package ru.spb.ifmo.tomita.dictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import ru.spb.ifmo.tomita.FileObject;
import ru.spb.ifmo.tomita.FileUtil;

/**
 * Пользовательский словарь Томиты. Состоит из списка импортируемых словарей и
 * набора объектов словаря определенного типа. Тип объектов словаря определяется
 * пользователем
 * 
 * @author nikit
 *
 * @param <T>
 *            тип объектов словаря
 */
public class UserDictionary<T extends DictionaryObject> extends Dictionary
        implements FileObject {

    private final Collection<T> objects;

    private final Collection<Dictionary> imports;

    /**
     * @param name
     *            имя словаря
     */
    public UserDictionary(String name) {
        this(name, DEFAULT_SUFFIX);
    }

    /**
     * @param name
     *            имя словаря
     * @param suffix
     *            суффикс
     */
    public UserDictionary(String name, String suffix) {
        super(name, suffix);
        objects = new ArrayList<>();
        imports = new HashSet<>();
    }

    @Override
    public void writeTo(File outDir) {
        File outFile = new File(outDir, getFileName());
        FileUtil.writeToFile(outFile, toString());
    }

    /**
     * Добавить объект в словарь
     * 
     * @param object
     *            объект словаря
     * 
     * @see Collection#add(Object)
     */
    public void addObject(T object) {
        objects.add(object);
    }

    /**
     * Удалить объект из словаря
     * 
     * @param object
     *            объект словаря
     * @return <code>true</code>, если удаление прошло успешно,
     *         <code>false</code> в противном случае
     * 
     * @see Collection#remove(Object)
     */
    public boolean deleteObject(T object) {
        return objects.remove(object);
    }

    /**
     * Получить набор объектов словаря
     * 
     * @return объекты в словаре. Если словарь пуст, возвратится пустая
     *         коллекция
     */
    public final Collection<T> getObjects() {
        return Collections.unmodifiableCollection(objects);
    }

    /**
     * Получить набор импортируемых словарей
     * 
     * @return набор словарей, импортируемых в данный. Если никаких словарей не
     *         импортируется, возвращается пустая коллекция
     */
    public final Collection<Dictionary> getImports() {
        return Collections.unmodifiableCollection(imports);
    }

    /**
     * Добавить словарь к списку импортируемых
     * 
     * @param dictionary
     *            импортируемый словарь. Не может быть <code>null</code>. Если
     *            попытаться импортировать словарь сам в себя - словарь не
     *            добавится в список импортируемых
     */
    public final void importDictionary(Dictionary dictionary) {
        if (dictionary != null && this != dictionary) {
            imports.add(dictionary);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        imports.forEach(dict -> result.append("import \"")
                .append(dict.getFileName()).append("\";\n"));

        objects.forEach(o -> result.append(o.formatString()).append('\n'));
        return result.toString();
    }
}
