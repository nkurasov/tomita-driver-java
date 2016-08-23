package ru.spb.ifmo.fact.util;

import java.util.Collection;

/**
 * Утилитные методы для проверки значений
 * 
 * @author nikit
 *
 */
public final class CheckUtil {

    private CheckUtil() {
        // do nothing
    }

    /**
     * Проверить значение на <code>null</code>. Если значение равно
     * <code>null</code>, генерируется исключение {@link IllegalStateException}
     * с указанным сообщением об ошибке
     * 
     * @param value
     *            значение для проверки на <code>null</code>
     * @param errorMessage
     *            текст сообщения об ошибке
     */
    public static void shouldNotNull(Object value, String errorMessage) {
        if (value == null) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public static void shouldNotEmpty(Collection<?> values, String errorMessage) {
        CheckUtil.shouldNotNull(values, errorMessage);
        if (values.isEmpty()) {
            throw new IllegalStateException(errorMessage);
        }
    }

    public static void shouldInstanceOf(Object value,
            Class<? extends Object> requiredClass, String errorMessage) {
        CheckUtil.shouldNotNull(requiredClass,
                "Аргумент 'requiredClass' не может быть null");
        if (!requiredClass.isInstance(value)) {
            throw new IllegalStateException(errorMessage);
        }
    }
}
