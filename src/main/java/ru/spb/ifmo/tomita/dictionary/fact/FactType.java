package ru.spb.ifmo.tomita.dictionary.fact;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.DictionaryObject;

/**
 * Тип факта
 * 
 * @author nikit
 *
 */
public final class FactType implements DictionaryObject {

    private static final FactType BASE = new FactType();

    private final String name;

    private final FactType base;

    private final List<FactField> fields;

    /**
     * Конструктор для базового типа. Не предназначен для использования вне
     * класса
     */
    private FactType() {
        this.name = "NFactType.TFact";
        this.base = this;
        this.fields = Collections.emptyList();
    }

    /**
     * Тип факта, наследованный от {@link FactType#baseType()} с указанным
     * именем
     * 
     * @param name
     *            имя факта
     */
    public FactType(String name) {
        this(name, baseType());
    }

    /**
     * Конструирует тип факта с указанным родительским типом и именем
     * 
     * @param name
     *            имя факта
     * @param base
     *            базовый тип факта
     */
    public FactType(String name, FactType base) {
        CheckUtil.shouldNotNull(name, "Не задано название типа факта");
        this.name = name;
        CheckUtil.shouldNotNull(base, "Не задан базовый тип факта");
        this.base = base;
        this.fields = new ArrayList<>();
    }

    /**
     * Добавить обязательное поле факта. Если поле с указанным именем уже
     * существует, новое поле не будет добавлено
     * 
     * @param fieldType
     *            тип поля
     * @param name
     *            имя поля
     */
    public void addRequiredField(FactFieldType fieldType, String name) {
        addField(FactField.createRequiredField(this, fieldType, name,
                getNextOrder()));
    }

    /**
     * Добавить обязательное поле факта. Если поле с указанным именем уже
     * существует, новое поле не будет добавлено
     * 
     * @param fieldType
     *            тип поля
     * @param name
     *            имя поля
     * @param defaultValue
     *            значение по умолчанию
     */
    public void addRequiredField(FactFieldType fieldType, String name,
            Object defaultValue) {
        addField(FactField.createRequiredField(this, fieldType, name,
                getNextOrder(), defaultValue));
    }

    /**
     * Добавить необязательное поле факта. Если поле с указанным именем уже
     * существует, новое поле не будет добавлено
     * 
     * @param fieldType
     *            тип поля
     * @param name
     *            имя поля
     */
    public void addOptionalField(FactFieldType fieldType, String name) {
        addField(FactField.createOptionalField(this, fieldType, name,
                getNextOrder()));
    }

    /**
     * Добавить необязательное поле факта. Если поле с указанным именем уже
     * существует, новое поле не будет добавлено
     * 
     * @param fieldType
     *            тип поля
     * @param name
     *            имя поля
     * @param defaultValue
     *            значение по умолчанию
     */
    public void addOptionalField(FactFieldType fieldType, String name,
            Object defaultValue) {
        addField(FactField.createOptionalField(this, fieldType, name,
                getNextOrder(), defaultValue));
    }

    /**
     * Добавить поле факта. Если поле с указанным именем уже существует, новое
     * поле не будет добавлено
     * 
     * @param field
     *            поле факта для добавления
     */
    protected void addField(FactField field) {
        if (!containsField(field.getName())) {
            fields.add(field);
        }
    }

    /**
     * Проверить существование поля в факте с указанным именем
     * 
     * @param name
     *            имя поля
     * @return <code>true</code>, если поле факта с таким именем существует,
     *         <code>false</code> в обратном случае
     */
    public boolean containsField(String name) {
        return getField(name).isPresent();
    }

    /**
     * Получить экземпляр FactField, представляющий поле факта
     * 
     * @param name
     *            имя требуемого поля
     * @return объект {@link Optional}, содержащий поле с указанным именем. Если
     *         поля с таким именем не было найдено, вернется пустой объект
     *         {@link Optional#empty()}
     */
    public Optional<FactField> getField(String name) {
        return fields.stream().filter(f -> f.getName().equals(name)).findAny();
    }

    @Override
    public String formatString() {
        StringBuilder result = new StringBuilder();
        result.append("message ").append(name).append(" : ")
                .append(base.getName()).append('\n').append('{').append('\n');
        result.append(String.join("\n", mapFieldsToString()));
        result.append('\n').append('}');
        return result.toString();
    }

    /**
     * Преобразовать набор полей в строковый формат
     * 
     * @return набор полей в строковом виде
     */
    private Collection<String> mapFieldsToString() {
        return fields.stream().map(DictionaryObject::formatString)
                .collect(Collectors.toList());
    }

    /**
     * @return следующий порядковый номер поля
     */
    private int getNextOrder() {
        return fields.size() + 1;
    }

    /**
     * @return родительский тип факта
     */
    public FactType getBase() {
        return base;
    }

    /**
     * @return имя факта
     */
    public String getName() {
        return name;
    }

    /**
     * @return список полей факта
     */
    public List<FactField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    /**
     * @return базовый тип фактов
     */
    public static FactType baseType() {
        return BASE;
    }
}
