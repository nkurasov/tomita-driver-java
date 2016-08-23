package ru.spb.ifmo.tomita.dictionary.fact;

import ru.spb.ifmo.fact.util.CheckUtil;

/**
 * Форматтеры для представления значений полей в строковый вид
 * 
 * @author nikit
 *
 */
/* package */final class Formatters {

    private static final String INCOMPATIBLE_VALUE_TYPE = "Значение несовместимого типа";

    private static final ValueFormatter STRING_FORMATTER = new ValueFormatter() {

        @Override
        public String valueToString(Object value) {
            return "\"" + value + "\"";
        }
    };

    private static final ValueFormatter BOOL_FORMATTER = new ValueFormatter() {

        @Override
        public String valueToString(Object value) {
            CheckUtil.shouldInstanceOf(value, Boolean.class,
                    INCOMPATIBLE_VALUE_TYPE);
            Boolean boolValue = (Boolean) value;
            return (boolValue) ? "1" : "0";
        }
    };

    private Formatters() {
        // do nothing
    }

    /**
     * @return строковый форматтер
     */
    public static ValueFormatter stringFormat() {
        return STRING_FORMATTER;
    }

    /**
     * @return форматтер для логических значений
     */
    public static ValueFormatter boolFormat() {
        return BOOL_FORMATTER;
    }
}
