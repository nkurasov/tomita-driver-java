package ru.spb.ifmo.fact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import ru.spb.ifmo.fact.util.CheckUtil;

public class LinkWrapper implements Link {

    private final Link sourceLink;

    private final Link delegateLink;

    public LinkWrapper(Link sourceLink, Link delegateLink) {
        CheckUtil.shouldNotNull(sourceLink, "Не указана текущая ссылка");
        this.sourceLink = sourceLink;
        CheckUtil.shouldNotNull(delegateLink, "Не указана следующая ссылка");
        this.delegateLink = delegateLink;
    }

    public LinkWrapper(String name, Link delegateLink) {
        this(new DirectLink(name), delegateLink);
    }

    @Override
    public String getId() {
        return sourceLink.getId() + "." + delegateLink.getId();
    }

    @Override
    public NamedToken selectToken(Collection<? extends Token> tokens) {
        Token selected = sourceLink.selectToken(tokens);
        if (selected instanceof WordSeq) {
            return delegateLink.selectToken(((WordSeq) selected).getTokens());
        } else if (selected instanceof SynonymSet) {
            return delegateLink
                    .selectToken(((SynonymSet) selected).getTokens());
        }

        throw new NoSuchElementException(
                "Элемента с таким идентификатором не существует");
    }

    public static Link parse(String... idents) {
        CheckUtil.shouldNotEmpty(Arrays.asList(idents),
                "Не задан список идентификаторов");
        List<String> tokenNames = new ArrayList<>(Arrays.asList(idents));
        if (tokenNames.size() == 1) {
            return new DirectLink(tokenNames.get(0));
        }

        Collections.reverse(tokenNames);
        return tokenNames.stream().map(DirectLink::new).map(Link.class::cast)
                .reduce((a, b) -> new LinkWrapper(b, a)).get();
    }
}
