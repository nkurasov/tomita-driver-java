package ru.spb.ifmo.tomita.dictionary.grammar;

import ru.spb.ifmo.fact.util.CheckUtil;

public abstract class UnaryOperator implements Operator {

    private final String symbolicName;

    private final Symbol symbol;

    public UnaryOperator(String symbolicName, Symbol symbol) {
        CheckUtil.shouldNotNull(symbolicName, "Не задано название оператора");
        this.symbolicName = symbolicName;
        CheckUtil.shouldNotNull(symbol,
                "Не задан символ, для которого определяется оператор");
        this.symbol = symbol;
    }

    @Override
    public String getSymbolicName() {
        return symbolicName;
    }

    public Symbol getSymbol() {
        return symbol;
    }
}
