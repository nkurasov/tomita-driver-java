package ru.spb.ifmo.tomita.dictionary;

/**
 * Словарь Томиты
 * 
 * @author nikit
 *
 */
public abstract class Dictionary {

    /**
     * Суффикс файла словаря по умолчанию
     */
    public static final String DEFAULT_SUFFIX = "proto";

    private final String name;

    private String suffix;

    /**
     * Конструируем экземпляр словаря с указанным именем
     * 
     * @param name
     *            имя словаря
     */
    public Dictionary(String name) {
        this(name, DEFAULT_SUFFIX);
    }

    /**
     * Конструируем экземпляр словаря с указанным именем и суффиксом имени файла
     * 
     * @param name
     *            имя словаря
     * @param suffix
     *            суффикс имени файла. Используется при записи словаря в файл
     */
    public Dictionary(String name, String suffix) {
        if (name == null) {
            throw new IllegalArgumentException(
                    "dictionaty name must be specified");
        }
        this.name = name;
        this.suffix = suffix;
    }

    /**
     * @return имя словаря
     */
    public final String getName() {
        return name;
    }

    /**
     * @return суффикс имени словаря
     */
    public final String getSuffix() {
        return suffix;
    }

    /**
     * Задать новый суффикс имени словаря
     * 
     * @param suffix
     *            суффикс
     */
    public final void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * Получить имя файла словаря. Состоит из имени словаря и суффикса,
     * разделенных точкой
     * 
     * @return имя файла, которое представляет словарь. Если в имени файла не
     *         задан суффикс - используется {@link #DEFAULT_SUFFIX значение по
     *         умолчанию}
     */
    public final String getFileName() {
        return name + "." + (suffix != null ? suffix : DEFAULT_SUFFIX);
    }
}
