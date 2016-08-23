package ru.spb.ifmo.tomita.dictionary.article;

import ru.spb.ifmo.tomita.dictionary.grammar.Grammar;

/**
 * Статья газеттира, представляющая ссылку на грамматику
 * 
 * @author nikit
 *
 */
public class GrammarArticle extends Article {

    private Grammar grammar;

    /**
     * @param grammar
     *            грамматика, на которую ссылается статья
     */
    public GrammarArticle(Grammar grammar) {
        super(grammar.getName());
        this.grammar = grammar;
        setProperty("key", "tomita:" + grammar.getFileName());
        getProperty("key").ifPresent(p -> p.setLitter("type", "CUSTOM"));
    }

    /**
     * @return грамматика
     */
    public Grammar getGrammar() {
        return grammar;
    }
}
