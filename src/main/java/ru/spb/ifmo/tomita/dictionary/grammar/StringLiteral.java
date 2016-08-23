package ru.spb.ifmo.tomita.dictionary.grammar;

/**
 * Терминальный символ, представляющий собой строку
 * 
 * @author nikit
 *
 */
public class StringLiteral extends Terminal {

    /**
     * @param literal
     *            строка
     */
    public StringLiteral(String literal) {
        super("\"" + literal + "\"");
    }
}
