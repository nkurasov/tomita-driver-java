package ru.spb.ifmo.tomita.dictionary.article;

/**
 * Тип статьи газеттира
 * 
 * @author nikit
 *
 */
public class ArticleType {

    private static final ArticleType BASE = new ArticleType("TAuxDicArticle");

    private final String typeName;

    /**
     * @param typeName
     *            имя типа
     */
    public ArticleType(String typeName) {
        if (typeName == null) {
            throw new IllegalArgumentException(
                    "name of article type must be specified");
        }
        this.typeName = typeName;
    }

    /**
     * @return имя типа статьи
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Получить базовый тип всех статей газеттира
     * 
     * @return базовый тип статьи
     */
    public static final ArticleType baseType() {
        return BASE;
    }
}
