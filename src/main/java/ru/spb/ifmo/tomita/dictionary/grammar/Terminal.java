package ru.spb.ifmo.tomita.dictionary.grammar;

import ru.spb.ifmo.fact.util.CheckUtil;

/**
 * Терминальный символ грамматики
 * 
 * @author nikit
 *
 */
public class Terminal implements Symbol {

    private final String literal;

    /**
     * @param literal
     *            значение символа
     */
    public Terminal(String literal) {
        CheckUtil.shouldNotNull(literal,
                "Не задано строковое представление терминального символа");
        this.literal = literal.trim();
    }

    /**
     * @return строковое представление символа
     */
    public String getLiteral() {
        return literal;
    }

    @Override
    public String formatString() {
        return literal;
    }
}
