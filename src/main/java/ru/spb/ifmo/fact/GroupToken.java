package ru.spb.ifmo.fact;

import java.util.Collection;

public interface GroupToken extends Token {

    Collection<Token> getTokens();
    
}
