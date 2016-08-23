package ru.spb.ifmo.tomita.dictionary.fact;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.DictionaryObject;

/**
 * Тип поля факта
 * 
 * @author nikit
 *
 */
public final class FactFieldType implements DictionaryObject {

    private static final FactFieldType STRING_TYPE = new FactFieldType(
            "string", Formatters.stringFormat());

    private static final FactFieldType BOOL_TYPE = new FactFieldType("bool",
            Formatters.boolFormat());

    private static final FactFieldType FIO_TYPE = new FactFieldType(
            "NFactType.TFio", Formatters.stringFormat());

    private static final FactFieldType DATE_TYPE = new FactFieldType(
            "NFactType.TDate", Formatters.stringFormat());

    private final String name;

    private final ValueFormatter valueFormatter;

    private FactFieldType(String name, ValueFormatter valueFormatter) {
        CheckUtil.shouldNotNull(name, "Не задано имя типа");
        this.name = name;
        CheckUtil.shouldNotNull(valueFormatter, "Не задан формат вывода");
        this.valueFormatter = valueFormatter;
    }

    @Override
    public String formatString() {
        return name;
    }

    /**
     * @return имя типа
     */
    public String getName() {
        return name;
    }

    /**
     * @return объект для форматирования значения поля в строковый формат
     */
    public ValueFormatter getValueFormatter() {
        return valueFormatter;
    }

    /**
     * Представить значение поля с строку в соотвествии с конкретным типом
     * 
     * @param value
     *            значение
     * @return строковое представление значения
     */
    public String valueToString(Object value) {
        return valueFormatter.valueToString(value);
    }

    /**
     * @return строковый тип
     */
    public static final FactFieldType stringType() {
        return STRING_TYPE;
    }

    /**
     * @return логический тип
     */
    public static final FactFieldType boolType() {
        return BOOL_TYPE;
    }

    /**
     * @return тип, представляющий ФИО
     */
    public static final FactFieldType fioType() {
        return FIO_TYPE;
    }

    /**
     * @return тип, представляющий дату
     */
    public static final FactFieldType dateType() {
        return DATE_TYPE;
    }
}
