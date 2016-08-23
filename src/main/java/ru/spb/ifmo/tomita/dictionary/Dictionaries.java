package ru.spb.ifmo.tomita.dictionary;

import ru.spb.ifmo.tomita.dictionary.article.Article;
import ru.spb.ifmo.tomita.dictionary.fact.FactType;

/**
 * Предоставляет методы для создания словарей и доступа к стандартным словарям
 * tomit'ы
 * 
 * @author nikit
 *
 */
public final class Dictionaries {

    private static final StandartDictionary BASE = new StandartDictionary(
            "base");

    private static final StandartDictionary ARTICLES = new StandartDictionary(
            "articles_base");

    private static final StandartDictionary FACTTYPES = new StandartDictionary(
            "facttypes_base");

    private Dictionaries() {
        // do nothing
    }

    /**
     * Получить словарь базовых типов данных
     * 
     * @return базовый словарь типов
     */
    public static final StandartDictionary base() {
        return BASE;
    }

    /**
     * Получить словарь базовых типов статей газеттира
     * 
     * @return базовый словарь статей
     */
    public static StandartDictionary articles() {
        return ARTICLES;
    }

    /**
     * Получить словарь базовых типов фактов
     * 
     * @return базовый словарь типов фактов
     */
    public static StandartDictionary factTypes() {
        return FACTTYPES;
    }

    /**
     * Создать экземпляр газеттира. Хранит {@link Article статьи} и ссылки на
     * граммматики
     * 
     * @param name
     *            имя словаря
     * @return новый экземпляр словаря
     */
    public static UserDictionary<Article> createGazetteer(String name) {
        return new Gazetteer(name);
    }

    /**
     * Создать экземпляр словаря типов фактов
     * 
     * @param name
     *            имя словаря
     * @return новый экземпляр словаря
     */
    public static UserDictionary<FactType> createFactTypesDictionary(String name) {
        return new FactTypesDictionary(name);
    }
}
