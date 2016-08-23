package ru.spb.ifmo.fact;

import java.io.Reader;
import java.util.Set;

import ru.spb.ifmo.fact.util.CheckUtil;

public abstract class AbstractFactSearch implements FactSearch {

    private final FactDictionary dictionary;

    public AbstractFactSearch(FactDictionary dictionary) {
        CheckUtil.shouldNotNull(dictionary, "Словарь не задан");
        this.dictionary = dictionary;
    }

    @Override
    public FactDictionary getDictionary() {
        return dictionary;
    }
    
    @Override
    public final Set<Fact> search(Reader in, FactPattern pattern) {
        // FIXME преобразовать шаблон факта
        return runParser(in, pattern.getToken());
    }
    
    protected abstract Set<Fact> runParser(Reader in, Token rootToken);
}
