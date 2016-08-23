package ru.spb.ifmo.tomita;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import ru.spb.ifmo.fact.FactDictionary;
import ru.spb.ifmo.fact.GroupToken;
import ru.spb.ifmo.fact.GroupWord;
import ru.spb.ifmo.fact.LinkToken;
import ru.spb.ifmo.fact.NamedToken;
import ru.spb.ifmo.fact.SynonymSet;
import ru.spb.ifmo.fact.Token;
import ru.spb.ifmo.fact.Word;
import ru.spb.ifmo.fact.WordSeq;
import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.fact.FactField;
import ru.spb.ifmo.tomita.dictionary.grammar.Grammar;
import ru.spb.ifmo.tomita.dictionary.grammar.Operators;
import ru.spb.ifmo.tomita.dictionary.grammar.Rule;
import ru.spb.ifmo.tomita.dictionary.grammar.RuleElement;
import ru.spb.ifmo.tomita.dictionary.grammar.StringLiteral;
import ru.spb.ifmo.tomita.dictionary.grammar.Symbol;
import ru.spb.ifmo.tomita.dictionary.grammar.Terminal;

public class GrammarFactory {

    private String prefix = "S";

    private final FactDictionary dictionary;

    protected GrammarFactory(FactDictionary dictionary) {
        CheckUtil.shouldNotNull(dictionary, "Словарь не задан");
        this.dictionary = dictionary;
    }

    public Grammar createGrammar(String name, FactField interpTo,
            Token rootToken) {
        final int order = 0;
        Collection<Rule> rules = new ArrayList<>();

        Rule root = createRule(prefix, order, Collections.singleton(rootToken),
                new RootRuleSupplier(interpTo), rules);

        return new Grammar(name, root.getNonTerminalId(), rules);
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public FactDictionary getDictionary() {
        return dictionary;
    }

    private Rule createRule(String name, final int order,
            Collection<Token> tokens, RuleElementSupplier supplier,
            Collection<Rule> outRules) {
        int next = order + 1;
        Collection<RuleElement> elements = new ArrayList<RuleElement>();

        for (Token token : tokens) {
            if (token instanceof Word) {
                Word word = (Word) token;
                elements.add(new StringLiteral(word.getLiteral()));
            } else if (token instanceof WordSeq) {
                WordSeq words = (WordSeq) token;
                Rule rule = createRule(getTokenName(words, next), token, next,
                        outRules);
                next++;
                elements.add(rule.createSymbol());
            } else if (token instanceof SynonymSet) {
                SynonymSet synonyms = (SynonymSet) token;
                Rule rule = createRule(getTokenName(synonyms, next), token,
                        next, outRules);
                next++;
                elements.add(rule.createSymbol());
            } else if (token instanceof GroupWord) {
                GroupWord group = (GroupWord) token;
                elements.add(new Terminal(group.getGroupName()));
            } else if (token instanceof LinkToken) {
                LinkToken link = (LinkToken) token;
                NamedToken selected = link.selectToken(getDictionary());
                Rule rule = createRule(getTokenName(link), selected, next++,
                        outRules);
                elements.add(rule.createSymbol());
            }
        }

        Optional<Rule> result = findRule(name, outRules);
        if (result.isPresent()) {
            return result.get();
        } else {
            Rule newRule = new Rule(name, supplier.configureElements(elements));
            outRules.add(newRule);
            return newRule;
        }
    }

    private Rule createRule(String name, Token token, int order,
            Collection<Rule> outRules) {
        return createRule(name, order, extractTokens(token),
                getSupplier(token), outRules);
    }

    private Optional<Rule> findRule(String name, Collection<Rule> rules) {
        return rules.stream()
                .filter(r -> r.createSymbol().getId().equals(name)).findFirst();
    }

    /**
     * Выбрать экземпляр {@link RuleElementSupplier} для элемента
     * 
     * @param token
     * @return
     */
    private static RuleElementSupplier getSupplier(Token token) {
        if (token instanceof SynonymSet) {
            return SynonymsSupplier.instance();
        }

        return SeqSupplier.instance();
    }

    private static Collection<Token> extractTokens(Token token) {
        if (token instanceof GroupToken) {
            GroupToken group = (GroupToken) token;
            return group.getTokens();
        } else {
            return Collections.singleton(token);
        }
    }

    private String getTokenName(NamedToken token, int order) {
        if (token.getName().isPresent()) {
            return token.getName().get();
        } else {
            return prefix + order;
        }
    }

    private String getTokenName(LinkToken link) {
        return link.getFullId().replaceAll("\\.", "0");
    }

    /**
     * Создать новый экземпляр фабрики
     * 
     * @param dictionary
     * @return
     */
    public static GrammarFactory newFactory(FactDictionary dictionary) {
        return new GrammarFactory(dictionary);
    }

    @FunctionalInterface
    private static interface RuleElementSupplier {
        Collection<RuleElement> configureElements(Collection<RuleElement> source);
    }

    private static class SynonymsSupplier implements RuleElementSupplier {

        private static final RuleElementSupplier INSTANCE = new SynonymsSupplier();

        @Override
        public Collection<RuleElement> configureElements(
                Collection<RuleElement> source) {
            return Collections.singleton(Operators.or(source.stream()
                    .map(Symbol.class::cast).toArray(Symbol[]::new)));
        }

        public static RuleElementSupplier instance() {
            return INSTANCE;
        }
    }

    private static class SeqSupplier implements RuleElementSupplier {

        private static final RuleElementSupplier INSTANCE = new SeqSupplier();

        @Override
        public Collection<RuleElement> configureElements(
                Collection<RuleElement> source) {
            return source;
        }

        public static RuleElementSupplier instance() {
            return INSTANCE;
        }
    }

    private static class RootRuleSupplier implements RuleElementSupplier {

        private final FactField field;

        public RootRuleSupplier(FactField field) {
            CheckUtil.shouldNotNull(field,
                    "Не задано поле факта для интерпретации");
            this.field = field;
        }

        @Override
        public Collection<RuleElement> configureElements(
                Collection<RuleElement> source) {
            if (source.size() != 1) {
                throw new IllegalStateException(
                        "Неверное количество элементов правила");
            }
            Symbol[] symbols = source.stream().toArray(Symbol[]::new);
            return Collections.singleton(Operators.interp(symbols[0], field));
        }
    }
}
