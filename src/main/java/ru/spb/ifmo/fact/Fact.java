package ru.spb.ifmo.fact;

import ru.spb.ifmo.fact.util.CheckUtil;

public final class Fact {

    private final String text;

    public Fact(String text) {
        CheckUtil.shouldNotNull(text, "Не задан текст факта");
        this.text = text;
    }

    /**
     * @return текст факта
     */
    public String getText() {
        return text;
    }
}
