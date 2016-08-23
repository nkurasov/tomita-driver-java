package ru.spb.ifmo.fact;

import java.util.Arrays;

import ru.spb.ifmo.fact.util.CheckUtil;

public final class GroupWord extends AbstractNamedToken implements Token {

    private final String groupName;

    private GroupWord(String groupName) {
        CheckUtil.shouldNotNull(groupName, "Не задано имя символа");
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "GroupWord [groupName=" + groupName + "]";
    }

    public static String[] getSupportedNames() {
        return new String[] { "Noun", "Adv", "Adj", "OrdinalNumeral",
                "Participle", "Verb", "Prep" };
    }

    public static boolean isSupportedName(String name) {
        return Arrays.stream(getSupportedNames()).anyMatch(s -> s.equals(name));
    }

    public static GroupWord create(String name) {
        if (!isSupportedName(name)) {
            throw new IllegalArgumentException("Имя символа (" + name
                    + ") не поддерживается");
        }

        return new GroupWord(name);
    }

    /**
     * @return существительное
     */
    public static GroupWord noun() {
        return new GroupWord("Noun");
    }

    /**
     * @return прилагательное
     */
    public static GroupWord adj() {
        return new GroupWord("Adj");
    }

    /**
     * @return порядковое числительное
     */
    public static GroupWord ordinalNum() {
        return new GroupWord("OrdinalNumeral");
    }

    /**
     * @return наречие
     */
    public static GroupWord adv() {
        return new GroupWord("Adv");
    }

    /**
     * @return причастие
     */
    public static GroupWord participle() {
        return new GroupWord("Participle");
    }

    /**
     * @return глагол
     */
    public static GroupWord verb() {
        return new GroupWord("Verb");
    }

    /**
     * @return предлог
     */
    public static GroupWord prep() {
        return new GroupWord("Prep");
    }
}
