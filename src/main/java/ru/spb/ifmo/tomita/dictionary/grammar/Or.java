package ru.spb.ifmo.tomita.dictionary.grammar;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * Оператор ИЛИ между символами грамматики
 * 
 * @author nikit
 *
 */
public class Or implements Operator, Iterable<Symbol> {

    private static final String OR_NAME = "|";

    private Symbol[] symbols;

    /**
     * @param symbols
     *            символы грамматики
     */
    public Or(Symbol... symbols) {
        if (symbols.length < 2) {
            throw new IllegalStateException(
                    "Операция ИЛИ определяется над двумя или более символами правила");
        }
        this.symbols = symbols;
    }

    /**
     * @return символы грамматики
     */
    public Symbol[] getSymbols() {
        return symbols.clone();
    }

    @Override
    public Iterator<Symbol> iterator() {
        return Arrays.asList(symbols).iterator();
    }

    @Override
    public String getSymbolicName() {
        return OR_NAME;
    }

    @Override
    public String formatString() {
        return String.join(" " + getSymbolicName() + " ", mapSymbolsToString());
    }

    private Iterable<String> mapSymbolsToString() {
        return Arrays.stream(symbols).map(Symbol::formatString)
                .collect(Collectors.toList());
    }
}
