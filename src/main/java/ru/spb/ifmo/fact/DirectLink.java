package ru.spb.ifmo.fact;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.spb.ifmo.fact.util.CheckUtil;

public class DirectLink implements Link {

    private String tokenName;

    public DirectLink(String tokenName) {
        CheckUtil.shouldNotNull(tokenName,
                "Имя элемента, на которое указывает ссылка, не задано");
        this.tokenName = tokenName;
    }

    @Override
    public NamedToken selectToken(Collection<? extends Token> tokens) {
        if (tokens == null) {
            throw new NoSuchElementException(
                    "Отсутствует список доступных элементов");
        }

        Optional<NamedToken> token = tokens.stream()
                .filter(NamedToken.class::isInstance)
                .map(NamedToken.class::cast)
                .filter(t -> t.getName().isPresent())
                .filter(t -> tokenName.equals(t.getName().get())).findFirst();
        token.orElseThrow(() -> elementNotFound(tokenName, tokens));
        return token.get();
    }

    private static NoSuchElementException elementNotFound(String tokenName,
            Collection<? extends Token> tokens) {
        return new NoSuchElementException("Элемент с именем ("
                + tokenName
                + ") отсутствует в списке элементов ("
                + tokens.stream().filter(NamedToken.class::isInstance)
                        .map(NamedToken.class::cast)
                        .filter(t -> t.getName().isPresent())
                        .map(t -> t.getName().get())
                        .collect(Collectors.toList()) + ")");
    }

    @Override
    public String getId() {
        return tokenName;
    }
}
