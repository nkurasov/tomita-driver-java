package ru.spb.ifmo.fact;

public class ExistTokenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExistTokenException(NamedToken token) {
        super("Элемент с именем (" + token.getName()
                + ") уже существует в словаре");
    }
}
