package ru.spb.ifmo.tomita.dictionary.grammar;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.fact.FactField;

/**
 * Оператор interp грамматики. Определяет, в какое именно поле факта записать
 * значение этого символа грамматики
 * 
 * @author nikit
 *
 */
public final class Interp extends UnaryOperator {

    private final FactField field;

    /**
     * @param symbol
     *            символ грамматики
     * @param field
     *            поле факта
     */
    public Interp(Symbol symbol, FactField field) {
        super("interp", symbol);
        CheckUtil.shouldNotNull(field, "Не задано поле факта");
        this.field = field;
    }

    @Override
    public String formatString() {
        return getSymbol().formatString() + " " + getSymbolicName() + " ("
                + field.getFullName() + ")";
    }

    /**
     * @return поле факта
     */
    public FactField getField() {
        return field;
    }
}
