package ru.spb.ifmo.fact;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class WordSeq extends AbstractNamedToken implements GroupToken,
        Iterable<Token> {

    private final List<Token> tokens;

    public WordSeq(final Token... tokens) {
        this.tokens = Arrays.asList(tokens);
    }

    @Override
    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    @Override
    public String toString() {
        return "WordSeq [tokens=" + tokens + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((tokens == null) ? 0 : tokens.hashCode());
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
        WordSeq other = (WordSeq) obj;
        if (tokens == null) {
            if (other.tokens != null)
                return false;
        } else if (!tokens.equals(other.tokens))
            return false;
        return true;
    }
}
