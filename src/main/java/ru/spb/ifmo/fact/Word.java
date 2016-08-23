package ru.spb.ifmo.fact;

import ru.spb.ifmo.fact.util.CheckUtil;

public final class Word implements Token {

    private final String wordLiteral;

    public Word(final String wordLiteral) {
        CheckUtil.shouldNotNull(wordLiteral, "Слово не задано");
        this.wordLiteral = wordLiteral;
    }

    public String getLiteral() {
        return wordLiteral;
    }

    @Override
    public String toString() {
        return "Word [wordLiteral=" + wordLiteral + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((wordLiteral == null) ? 0 : wordLiteral.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Word other = (Word) obj;
        if (wordLiteral == null) {
            if (other.wordLiteral != null)
                return false;
        } else if (!wordLiteral.equals(other.wordLiteral))
            return false;
        return true;
    }
}
