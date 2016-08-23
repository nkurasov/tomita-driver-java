package ru.spb.ifmo.fact;

import java.util.Collection;

public interface Link {

    String getId();

    NamedToken selectToken(Collection<? extends Token> tokens);
}
