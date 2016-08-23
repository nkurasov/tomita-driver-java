package ru.spb.ifmo.fact;

public class LinkToken implements Token {

    private final Link sourceLink;

    public LinkToken(String... idents) {
        sourceLink = LinkWrapper.parse(idents);
    }

    public Link getSourceLink() {
        return sourceLink;
    }

    public String getFullId() {
        return sourceLink.getId();
    }

    public NamedToken selectToken(FactDictionary dictionary) {
        return sourceLink.selectToken(dictionary.getTokens());
    }

    @Override
    public String toString() {
        return "LinkToken [ident=" + getFullId() + "]";
    }
}
