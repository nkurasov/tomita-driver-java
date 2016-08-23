package ru.spb.ifmo.tomita.dictionary.grammar;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.DictionaryObject;

/**
 * Правило грамматики
 * 
 * @author nikit
 *
 */
public class Rule implements Iterable<RuleElement>, DictionaryObject {

    private final String nonTerminalId;

    private final Collection<RuleElement> elements;

    /**
     * @param nonTerminalId
     *            имя определяемого нетерминала (в правой части правила)
     * @param elements
     *            элементы левой части правила
     */
    public Rule(String nonTerminalId, RuleElement... elements) {
        this(nonTerminalId, Arrays.asList(elements));
    }

    /**
     * @param nonTerminalId
     *            имя определяемого нетерминала (в правой части правила)
     * @param elements
     *            элементы левой части правила
     */
    public Rule(String nonTerminalId, Collection<RuleElement> elements) {
        CheckUtil.shouldNotNull(nonTerminalId,
                "Не задано имя символа, определяемого правилом");
        CheckUtil.shouldNotEmpty(elements, "Набор элементов правила пуст");
        this.nonTerminalId = nonTerminalId;
        this.elements = Collections.unmodifiableCollection(elements);
    }

    /**
     * @return имя нетерминала
     */
    public String getNonTerminalId() {
        return nonTerminalId;
    }

    /**
     * @return элементы левой части правила
     */
    public Collection<RuleElement> getElements() {
        return elements;
    }

    /**
     * @return экземпляр {@link NonTerminal}, с именем, определяемым правилом
     *         {@link #getNonTerminalId()}
     */
    public NonTerminal createSymbol() {
        return new NonTerminal(nonTerminalId);
    }

    @Override
    public Iterator<RuleElement> iterator() {
        return elements.iterator();
    }

    @Override
    public String formatString() {
        return getNonTerminalId() + " -> "
                + String.join(" ", mapElementsToString());
    }

    private Iterable<String> mapElementsToString() {
        return elements.stream().map(RuleElement::formatString)
                .collect(Collectors.toList());
    }

    // SHIT переделать потом на че-нибудь классное
    public static class Builder {
        private final String nonTerminalId;
        private RuleElement[] elems;

        public Builder(String nonTerminalId) {
            this.nonTerminalId = nonTerminalId;
        }

        public Builder setElements(RuleElement... elems) {
            this.elems = elems;
            return this;
        }

        public Rule build() {
            return new Rule(nonTerminalId, elems);
        }
    }
}
