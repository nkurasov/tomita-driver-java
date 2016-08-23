package ru.spb.ifmo.fact;

import java.util.Set;

public interface FactDictionary {

    NamedToken getToken(String name);
    
    void addToken(NamedToken token);
    
    boolean containsToken(String name);
    
    Set<NamedToken> getTokens();
}
