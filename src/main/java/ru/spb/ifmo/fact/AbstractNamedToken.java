package ru.spb.ifmo.fact;

import java.util.Optional;

public abstract class AbstractNamedToken implements NamedToken {

    private String name;

    @Override
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
