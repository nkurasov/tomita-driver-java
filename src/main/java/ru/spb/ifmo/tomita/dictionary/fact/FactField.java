package ru.spb.ifmo.tomita.dictionary.fact;

import java.util.Optional;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.DictionaryObject;

/**
 * Поле факта
 * 
 * @author nikit
 *
 */
public final class FactField implements DictionaryObject {

    private boolean required;

    private final String name;

    private FactFieldType fieldType;

    private final Optional<Object> defaultValue;

    private int order;

    private FactType owner;

    private FactField(FactType owner, boolean required,
            FactFieldType fieldType, String name, int order,
            Optional<Object> defaultValue) {
        CheckUtil.shouldNotNull(owner, "Не задан тип факта");
        this.owner = owner;
        this.required = required;
        CheckUtil.shouldNotNull(fieldType, "Не задан тип поля факта");
        this.fieldType = fieldType;
        CheckUtil.shouldNotNull(name, "Не задано имя поля");
        this.name = name;
        this.order = order;
        this.defaultValue = defaultValue;
    }

    @Override
    public String formatString() {
        StringBuilder result = new StringBuilder();
        result.append(toStringRequired()).append(' ')
                .append(fieldType.formatString()).append(' ').append(name)
                .append(" = ").append(order);
        defaultValue.ifPresent(d -> result.append(" [ default = ")
                .append(defaultValueToString(d)).append(" ]"));
        result.append(';');
        return result.toString();
    }

    /**
     * @return полное имя поля (с именем типа факта)
     */
    public String getFullName() {
        return owner.getName() + "." + name;
    }

    /**
     * @return тип факта, которому принадлежит поле
     */
    public FactType getOwner() {
        return owner;
    }

    private String toStringRequired() {
        return (required) ? "required" : "optional";
    }

    private String defaultValueToString(Object value) {
        return fieldType.valueToString(value);
    }

    /**
     * @return значение по умолчанию
     */
    public Optional<Object> getDefaultValue() {
        return defaultValue;
    }

    /**
     * @return тип поля
     */
    public FactFieldType getFieldType() {
        return fieldType;
    }

    /**
     * @return <code>true</code>, если поле факта обязательное,
     *         <code>false</code>, если нет
     */
    public boolean isRequired() {
        return required;
    }

    /**
     * @return порядковый номер поля
     */
    public int getOrder() {
        return order;
    }

    /**
     * @return имя поля
     */
    public String getName() {
        return name;
    }

    /**
     * Создать обязательное поле факта
     * 
     * @param owner
     *            тип факта, которому принадлежит поле. Не может быть
     *            <code>null</code>
     * @param fieldType
     *            тип поля. Не может быть <code>null</code>
     * @param name
     *            имя поля. Не может быть <code>null</code>
     * @param order
     *            порядковый номер поля
     * @return новый экземпляр поля
     */
    public static FactField createRequiredField(FactType owner,
            FactFieldType fieldType, String name, int order) {
        return new FactField(owner, true, fieldType, name, order,
                Optional.empty());
    }

    /**
     * Создать обязательное поле факта
     * 
     * @param owner
     *            тип факта, которому принадлежит поле. Не может быть
     *            <code>null</code>
     * @param fieldType
     *            тип поля. Не может быть <code>null</code>
     * @param name
     *            имя поля. Не может быть <code>null</code>
     * @param order
     *            порядковый номер поля
     * @param defaultValue
     *            значение по умолчанию
     * @return новый экземпляр поля
     */
    public static FactField createRequiredField(FactType owner,
            FactFieldType fieldType, String name, int order, Object defaultValue) {
        return new FactField(owner, true, fieldType, name, order,
                Optional.ofNullable(defaultValue));
    }

    /**
     * Создать необязательное поле факта
     * 
     * @param owner
     *            тип факта, которому принадлежит поле. Не может быть
     *            <code>null</code>
     * @param fieldType
     *            тип поля. Не может быть <code>null</code>
     * @param name
     *            имя поля. Не может быть <code>null</code>
     * @param order
     *            порядковый номер поля
     * @return новый экземпляр поля
     */
    public static FactField createOptionalField(FactType owner,
            FactFieldType fieldType, String name, int order) {
        return new FactField(owner, false, fieldType, name, order,
                Optional.empty());
    }

    /**
     * Создать необязательное поле факта
     * 
     * @param owner
     *            тип факта, которому принадлежит поле. Не может быть
     *            <code>null</code>
     * @param fieldType
     *            тип поля. Не может быть <code>null</code>
     * @param name
     *            имя поля. Не может быть <code>null</code>
     * @param order
     *            порядковый номер поля
     * @param defaultValue
     *            значение по умолчанию
     * @return новый экземпляр поля
     */
    public static FactField createOptionalField(FactType owner,
            FactFieldType fieldType, String name, int order, Object defaultValue) {
        return new FactField(owner, false, fieldType, name, order,
                Optional.ofNullable(defaultValue));
    }
}