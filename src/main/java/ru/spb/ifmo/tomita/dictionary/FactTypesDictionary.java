package ru.spb.ifmo.tomita.dictionary;

import ru.spb.ifmo.tomita.dictionary.fact.FactType;

/**
 * Реализация словаря типов фактов. По умолчанию импортирует
 * {@link Dictionaries#base()} и {@link Dictionaries#factTypes()}
 * 
 * @author nikit
 *
 */
/* package */class FactTypesDictionary extends UserDictionary<FactType> {

    /**
     * @param name
     *            имя словаря
     */
    public FactTypesDictionary(String name) {
        super(name);
        configureImports();
    }

    /**
     * @param name
     *            имя словаря
     * @param suffix
     *            суффикс
     */
    public FactTypesDictionary(String name, String suffix) {
        super(name, suffix);
        configureImports();
    }

    private void configureImports() {
        importDictionary(Dictionaries.base());
        importDictionary(Dictionaries.factTypes());
    }
}
