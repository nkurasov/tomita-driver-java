package ru.spb.ifmo.fact;

import ru.spb.ifmo.fact.util.CheckUtil;

public class FactPattern {

    private final Token token;

    public FactPattern(Token token) {
        CheckUtil.shouldNotNull(token, "Корневой символ не задан");
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "FactPattern [token=" + token + "]";
    }
}
