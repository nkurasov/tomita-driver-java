package ru.spb.ifmo.tomita.dictionary.grammar;

/**
 * Оператор повторения (1 или более)
 * 
 * @author nikit
 *
 */
public class Rep1 extends UnaryOperator {

    /**
     * @param symbol
     *            символ грамматики
     */
    public Rep1(Symbol symbol) {
        super("+", symbol);
    }

    @Override
    public String formatString() {
        return getSymbol().formatString() + getSymbolicName();
    }
}
