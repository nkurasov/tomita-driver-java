package ru.spb.ifmo.tomita.dictionary.fact;

/**
 * Объект, предоставляющий возможность представления значения поля факта в
 * строковый вид
 * 
 * @author nikit
 *
 */
/* package */interface ValueFormatter {

    /**
     * @param value
     *            значение
     * @return строковое представление значения
     */
    String valueToString(Object value);

}
