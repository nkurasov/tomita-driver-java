package ru.spb.ifmo.tomita.dictionary.grammar;

import ru.spb.ifmo.fact.util.CheckUtil;

/**
 * Нетерминальный символ грамматики
 * 
 * @author nikit
 *
 */
public class NonTerminal implements Symbol {

    private final String id;

    /**
     * @param id
     *            имя нетерминала
     */
    public NonTerminal(String id) {
        CheckUtil.shouldNotNull(id,
                "Не задано название нетерминального символа");
        this.id = id.trim();
    }

    /**
     * @return имя нетерминала
     */
    public String getId() {
        return id;
    }

    @Override
    public String formatString() {
        return id;
    }
}
