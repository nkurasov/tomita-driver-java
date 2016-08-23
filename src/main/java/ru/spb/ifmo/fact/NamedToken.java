package ru.spb.ifmo.fact;

import java.util.Optional;

public interface NamedToken extends Token {

    Optional<String> getName();

    void setName(String name);

}
