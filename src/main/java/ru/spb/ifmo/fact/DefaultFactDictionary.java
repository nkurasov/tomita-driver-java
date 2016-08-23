package ru.spb.ifmo.fact;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ru.spb.ifmo.fact.util.CheckUtil;

public class DefaultFactDictionary implements FactDictionary {

    private final Map<String, NamedToken> tokens = new HashMap<>();

    @Override
    public NamedToken getToken(String name) {
        return tokens.get(name);
    }

    @Override
    public void addToken(NamedToken token) {
        CheckUtil.shouldNotNull(token, "Элемент не задан");
        token.getName().orElseThrow(
                DefaultFactDictionary::nameNotPresentException);
        if (containsToken(token)) {
            throw new ExistTokenException(token);
        }
        tokens.put(token.getName().get(), token);
    }

    @Override
    public boolean containsToken(String name) {
        return tokens.containsKey(name);
    }

    @Override
    public Set<NamedToken> getTokens() {
        return tokens.values().stream().collect(Collectors.toSet());
    }

    public boolean containsToken(NamedToken token) {
        CheckUtil.shouldNotNull(token, "Элемент не задан");
        return containsToken(token.getName().orElse(null));
    }

    private static IllegalStateException nameNotPresentException() {
        return new IllegalStateException(
                "Для добавления в словарь токен должен иметь имя");
    }
}
