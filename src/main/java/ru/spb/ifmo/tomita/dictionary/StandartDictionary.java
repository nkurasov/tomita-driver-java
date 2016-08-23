package ru.spb.ifmo.tomita.dictionary;

/**
 * Стандартный словарь Томиты. Файлы этих словарей уже определены, создавать их
 * не требуется
 * 
 * @author nikit
 *
 */
public final class StandartDictionary extends Dictionary {

    /**
     * Конструируем новый экземпляр стандартного словаря с указанным именем
     * 
     * @param name
     *            имя словаря
     */
    public StandartDictionary(String name) {
        super(name);
    }

    /**
     * Конструируем новый экземпляр стандартного словаря с указанным именем и
     * суффиксом имени файла
     * 
     * @param name
     *            имя словаря
     * @param suffix
     *            суффикс имени файла
     * 
     * @see Dictionary#Dictionary(String, String)
     */
    public StandartDictionary(String name, String suffix) {
        super(name, suffix);
    }
}
