package ru.spb.ifmo.tomita;

/**
 * Формат вывода результатов работы парсера
 * 
 * @author nikit
 *
 */
public enum OutputFormat {

    /**
     * XML
     */
    XML("xml"),

    /**
     * Обычный текст
     */
    TEXT("text"),

    /**
     * Формат protobuf. Записывает в некоем бинарном виде, не читается
     */
    PROTO("proto");

    private String typeName;

    /**
     * @param typeName
     *            строковое представление формата
     */
    private OutputFormat(String typeName) {
        this.typeName = typeName;
    }

    /**
     * @return строковое представление формата
     */
    public String getTypeName() {
        return typeName;
    }
}
