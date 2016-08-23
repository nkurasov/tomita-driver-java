package ru.spb.ifmo.tomita.dictionary.article;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.DictionaryObject;

/**
 * Поле статьи
 * 
 * @author nikit
 *
 */
public class ArticleProperty implements DictionaryObject {

    private final String name;

    private String value;

    private final Map<String, String> litters;

    /**
     * @param name
     *            имя поля
     */
    public ArticleProperty(String name) {
        CheckUtil.shouldNotNull(name, "Не задано имя свойства");
        this.name = name;
        this.litters = new HashMap<>();
    }

    /**
     * @param name
     *            имя поля
     * @param value
     *            начальное значение
     */
    public ArticleProperty(String name, String value) {
        this(name);
        this.value = value;
    }

    /**
     * @return пометы поля
     */
    public Map<String, String> getLitters() {
        return Collections.unmodifiableMap(litters);
    }

    /**
     * @return имя поля
     */
    public String getName() {
        return name;
    }

    /**
     * @return значение поля
     */
    public String getValue() {
        return value;
    }

    /**
     * Задать новое значение полю
     * 
     * @param value
     *            значение поля
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Установить помету полю
     * 
     * @param litterName
     *            имя пометы
     * @param litterValue
     *            значение пометы
     */
    public void setLitter(String litterName, String litterValue) {
        litters.put(litterName, litterValue);
    }

    /**
     * @param litterName
     *            имя пометы
     * @return значение пометы
     */
    public String getLitter(String litterName) {
        return litters.get(litterName);
    }

    @Override
    public String formatString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(" = { \"").append(value).append("\" ");
        for (Entry<String, String> litter : litters.entrySet()) {
            result.append(litter.getKey()).append('=')
                    .append(litter.getValue()).append(' ');
        }
        result.append('}');
        return result.toString();
    }
}
