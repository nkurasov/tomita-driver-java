package ru.spb.ifmo.tomita.dictionary.grammar;

/**
 * Определяет список терминальных символов парсера tomita
 * 
 * @author nikit
 *
 */
public final class Terminals {

    private Terminals() {
        // do nothing
    }

    /**
     * @param literal
     *            строка
     * @return терминальный символ, представляющий собой строку
     */
    public static Terminal literal(String literal) {
        return new StringLiteral(literal);
    }

    /**
     * Возвращает терминальный символ, представляющий любую последовательность
     * символов без пробелов. Не рекомендуется пользоваться вместе с
     * конструкцией {@link Operators#rep(Symbol)}, так как это может сильно
     * замедлить работу парсера
     * 
     * @return любая последовательность символов без пробелов
     */
    public static Terminal anyWord() {
        return new Terminal("AnyWord");
    }

    /**
     * @return любое слово из букв русского или латинского алфавитов
     */
    public static Terminal word() {
        return new Terminal("Word");
    }

    /**
     * @return существительное
     */
    public static Terminal noun() {
        return new Terminal("Noun");
    }

    /**
     * @return прилагательное
     */
    public static Terminal adj() {
        return new Terminal("Adj");
    }

    /**
     * @return порядковое числительное
     */
    public static Terminal ordinalNum() {
        return new Terminal("OrdinalNumeral");
    }

    /**
     * @return наречие
     */
    public static Terminal adv() {
        return new Terminal("Adv");
    }

    /**
     * @return причастие
     */
    public static Terminal participle() {
        return new Terminal("Participle");
    }

    /**
     * @return глагол
     */
    public static Terminal verb() {
        return new Terminal("Verb");
    }

    /**
     * @return предлог
     */
    public static Terminal prep() {
        return new Terminal("Prep");
    }

    /**
     * @return слово "и"
     */
    public static Terminal and() {
        return new Terminal("SimConjAnd");
    }

    /**
     * @return двойные кавычки
     */
    public static Terminal quoteDouble() {
        return new Terminal("QuoteDbl");
    }

    /**
     * @return одинарные кавычки
     */
    public static Terminal quoteSingle() {
        return new Terminal("QuoteSng");
    }

    /**
     * @return открывающая скобка
     */
    public static Terminal leftBracket() {
        return new Terminal("LBracket");
    }

    /**
     * @return ззакрывающая скобка
     */
    public static Terminal rightBracket() {
        return new Terminal("RBracket");
    }

    /**
     * @return тире
     */
    public static Terminal hyphen() {
        return new Terminal("Hyphen");
    }

    /**
     * @return точка
     */
    public static Terminal punct() {
        return new Terminal("Punct");
    }

    /**
     * @return запятая
     */
    public static Terminal comma() {
        return new Terminal("Comma");
    }

    /**
     * @return двоеточие
     */
    public static Terminal colon() {
        return new Terminal("Colon");
    }

    /**
     * @return цепочка символов, включающая символ '%'
     */
    public static Terminal percent() {
        return new Terminal("Percent");
    }

    /**
     * @return цепочка символов, включающая символ '$'
     */
    public static Terminal dollar() {
        return new Terminal("Dollar");
    }

    /**
     * @return знак '+'
     */
    public static Terminal plusSign() {
        return new Terminal("PlusSign");
    }

    /**
     * @return признак конца предложения
     */
    public static Terminal endOfSentence() {
        return new Terminal("EOSent");
    }
}
