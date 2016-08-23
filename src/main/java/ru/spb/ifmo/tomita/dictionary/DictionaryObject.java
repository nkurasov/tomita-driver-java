package ru.spb.ifmo.tomita.dictionary;

/**
 * Представляет объект словаря Томиты. Объектом словаря может быть объект любой
 * структуры, который может быть представлет в формате, соответствующем словарю
 * 
 * @author nikit
 *
 */
public interface DictionaryObject {

    /**
     * Строковое представление объекта
     * 
     * @return строковое представление объекта
     */
    String formatString();
}
