package ru.spb.ifmo.tomita.dictionary.grammar;

import ru.spb.ifmo.tomita.dictionary.fact.FactField;

/**
 * Операторы в грамматиках
 * 
 * @author nikit
 *
 */
public final class Operators {

    private Operators() {
        // do nothing
    }

    /**
     * Оператор ИЛИ, применяется над символами правила
     * 
     * @param symbols
     * @return
     */
    public static Operator or(Symbol... symbols) {
        return new Or(symbols);
    }

    /**
     * Оператор повторения (1 или более), применяется над одним символом
     * 
     * @param symbol
     * @return
     */
    public static Operator rep1(Symbol symbol) {
        return new Rep1(symbol);
    }

    /**
     * Оператор повторения (0 или более)
     * 
     * @param symbol
     * @return
     */
    public static Operator rep(Symbol symbol) {
        return new Rep(symbol);
    }

    /**
     * Оператор интерпретации символа в значение поля факта
     * 
     * @param symbol
     *            символ правила
     * @param field
     *            поле факта
     * @return
     */
    public static Operator interp(Symbol symbol, FactField field) {
        return new Interp(symbol, field);
    }
}
