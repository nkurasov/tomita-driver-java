package ru.spb.ifmo.tomita.dictionary;

import ru.spb.ifmo.tomita.dictionary.article.Article;

/**
 * Реализация газеттира. По умолчанию импортирует {@link Dictionaries#base()} и
 * {@link Dictionaries#articles()}
 * 
 * @author nikit
 *
 */
/* package */class Gazetteer extends UserDictionary<Article> {

    /**
     * Суффикс словаря по умолчанию
     */
    public static final String DEFAULT_GAZETTEER_SUFFIX = "gzt";

    /**
     * @param name
     *            имя словаря
     */
    public Gazetteer(String name) {
        this(name, DEFAULT_GAZETTEER_SUFFIX);
    }

    /**
     * @param name
     *            имя словаря
     * @param suffix
     *            суффикс
     */
    public Gazetteer(String name, String suffix) {
        super(name, suffix);
        importDictionary(Dictionaries.base());
        importDictionary(Dictionaries.articles());
    }
}
