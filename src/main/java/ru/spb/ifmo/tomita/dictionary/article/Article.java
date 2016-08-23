package ru.spb.ifmo.tomita.dictionary.article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import ru.spb.ifmo.tomita.dictionary.DictionaryObject;

/**
 * Статья газеттира
 * 
 * @author nikit
 *
 */
public class Article implements DictionaryObject {

    private final ArticleType base;

    private final String name;

    private final Collection<ArticleProperty> properties;

    /**
     * Конструирует экземпляр статьи, наследованной от
     * {@link Articles#baseType() базового типа статей}
     * 
     * @param name
     *            название статьи
     */
    public Article(String name) {
        this(ArticleType.baseType(), name);
    }

    /**
     * @param baseType
     *            родительский тип статьи
     * @param name
     *            название статьи
     */
    public Article(ArticleType baseType, String name) {
        if (baseType == null) {
            throw new IllegalArgumentException("article type must be specified");
        }
        this.base = baseType;

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("article name must be specified");
        }
        this.name = name;
        this.properties = new ArrayList<ArticleProperty>();
    }

    @Override
    public String formatString() {
        StringBuilder result = new StringBuilder();
        result.append(base.getTypeName()).append(' ').append('\"').append(name)
                .append('\"').append("\n{\n");
        for (ArticleProperty property : properties) {
            result.append(property.formatString()).append('\n');
        }
        result.append("\n}");
        return result.toString();
    }

    /**
     * @return родительский тип статьи
     */
    public ArticleType getBase() {
        return base;
    }

    /**
     * @return название статьи
     */
    public String getName() {
        return name;
    }

    /**
     * Добавить поле в статью. Если поле с таким ключом уже существует, ему
     * присвоится новое значение
     * 
     * @param name
     *            имя поля
     * @param value
     *            значение
     */
    public void setProperty(String name, String value) {
        Optional<ArticleProperty> property = getProperty(name);
        property.ifPresent(p -> p.setValue(value));
        if (!property.isPresent()) {
            addProperty(name, value);
        }
    }

    /**
     * Добавить поле статьи в список
     * 
     * @param name
     *            имя поля
     * @param value
     *            значение
     */
    private void addProperty(String name, String value) {
        properties.add(new ArticleProperty(name, value));
    }

    /**
     * @param name
     *            имя поля
     * @return <code>true</code>, если поле с таким именем присутствует в
     *         статье, <code>false</code> в обратном случае
     */
    public boolean containsProperty(String name) {
        return getProperty(name).isPresent();
    }

    /**
     * @param name
     *            имя поля
     * @return экземпляр {@link ArticleProperty}, представляющий поле статьи
     */
    public Optional<ArticleProperty> getProperty(String name) {
        return properties.stream().filter(p -> p.getName().equals(name))
                .findFirst();
    }
}
