package ru.spb.ifmo.tomita.dictionary.fact;

import ru.spb.ifmo.fact.util.CheckUtil;

/**
 * Представляет ФИО
 * 
 * @author nikit
 *
 */
public class Fio {

    private final String surname;

    private final String name;

    private final String middleName;

    /**
     * @param surname
     *            фамилия
     * @param name
     *            имя
     * @param middleName
     *            отчество
     */
    public Fio(String surname, String name, String middleName) {
        CheckUtil.shouldNotNull(surname, "Не задана фамилия");
        this.surname = surname;
        CheckUtil.shouldNotNull(name, "Не задано имя");
        this.name = name;
        CheckUtil.shouldNotNull(middleName, "Не задано отчество");
        this.middleName = middleName;
    }

    /**
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * @return отчество
     */
    public String getMiddleName() {
        return middleName;
    }
}
