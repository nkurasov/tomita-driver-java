package ru.spb.ifmo.tomita.dictionary.grammar;

/**
 * Оператор повторения (0 или более)
 * 
 * @author nikit
 *
 */
public final class Rep extends UnaryOperator {

    /**
     * @param symbol
     *            символ грамматики
     */
    public Rep(Symbol symbol) {
        super("*", symbol);
    }

    @Override
    public String formatString() {
        return getSymbol().formatString() + getSymbolicName();
    }
}
