package ru.spb.ifmo.fact;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class SynonymSet extends AbstractNamedToken implements GroupToken,
        Iterable<Token> {

    private final Set<Token> tokens;

    public SynonymSet(final Token... tokens) {
        this.tokens = new HashSet<>(Arrays.asList(tokens));
    }

    @Override
    public Set<Token> getTokens() {
        return Collections.unmodifiableSet(tokens);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    @Override
    public String toString() {
        return "SynonymSet [tokens=" + tokens + "]";
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
        SynonymSet other = (SynonymSet) obj;
        if (tokens == null) {
            if (other.tokens != null)
                return false;
        } else if (!tokens.equals(other.tokens))
            return false;
        return true;
    }
}
