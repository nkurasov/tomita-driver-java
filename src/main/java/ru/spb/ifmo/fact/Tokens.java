package ru.spb.ifmo.fact;

import java.util.Arrays;

public final class Tokens {

    private Tokens() {
        // do nothing
    }

    public static Word word(String word) {
        return new Word(word);
    }

    public static WordSeq seq(Token... tokens) {
        return new WordSeq(tokens);
    }

    public static WordSeq seq(Object... tokens) {
        return new WordSeq(convertToTokens(tokens));
    }

    public static SynonymSet set(Token... tokens) {
        return new SynonymSet(tokens);
    }

    public static SynonymSet synonyms(Object... tokens) {
        return new SynonymSet(convertToTokens(tokens));
    }

    public static LinkToken link(String... idents) {
        return new LinkToken(idents);
    }

    private static Token[] convertToTokens(Object... objects) {
        return Arrays.stream(objects).map(Tokens::tokenize)
                .toArray(Token[]::new);
    }

    private static Token tokenize(Object value) {
        if (value instanceof Token) {
            return (Token) value;
        } else if (value instanceof String) {
            return new Word((String) value);
        } else {
            throw new IllegalStateException("Значение неподдерживаемого типа: "
                    + value);
        }
    }
}
