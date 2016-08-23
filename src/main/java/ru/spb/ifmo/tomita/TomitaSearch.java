package ru.spb.ifmo.tomita;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import ru.spb.ifmo.fact.AbstractFactSearch;
import ru.spb.ifmo.fact.DefaultFactDictionary;
import ru.spb.ifmo.fact.Fact;
import ru.spb.ifmo.fact.FactDictionary;
import ru.spb.ifmo.fact.Token;
import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.fact.FactFieldType;
import ru.spb.ifmo.tomita.dictionary.fact.FactType;
import ru.spb.ifmo.tomita.dictionary.grammar.Grammar;

public class TomitaSearch extends AbstractFactSearch {

    private static final String FACT_NAME = "UserFact";

    private static final String FACT_TEXT = "FactText";

    private static final String GRAMMAR_NAME = "UserGrammar";

    public TomitaSearch() {
        this(new DefaultFactDictionary());
    }

    public TomitaSearch(FactDictionary dictionary) {
        super(dictionary);
    }

    @Override
    public Set<Fact> runParser(Reader in, Token root) {
        if (in == null) {
            return Collections.emptySet();
        }

        CheckUtil.shouldNotNull(root, "Не задан корневой элемент для поиска");

        File input = FileUtil.createTempFile("input", "txt");
        FileUtil.writeTo(input, in);

        FactType factType = createFactType();
        Grammar grammar = GrammarFactory.newFactory(getDictionary())
                .createGrammar(GRAMMAR_NAME,
                        factType.getField(FACT_TEXT).get(), root);

        System.out.println("running tomita on: " + input.getAbsolutePath());
        Reader results = runTomita(input.getAbsolutePath(), grammar, factType);

        Set<Fact> facts = new HashSet<>();
        Document document = readXmlDocument(results);
        NodeList factNodes = getFactNodes(document);
        for (int i = 0; i < factNodes.getLength(); i++) {
            Node child = factNodes.item(i);
            String factText = child.getNodeValue();
            facts.add(new Fact(factText));
        }
        return facts;
    }

    private static NodeList getFactNodes(Document document) {
        try {
            XPathExpression expr = XPathFactory.newInstance().newXPath()
                    .compile("//document/facts/*/FactText/@val");
            return (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new TomitaException(e);
        }
    }

    private static Document readXmlDocument(Reader in) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(in));
            return document;
        } catch (SAXException | IOException | ParserConfigurationException e) {
            throw new TomitaException("Ошибка при разборе результатов", e);
        }

    }

    private static FactType createFactType() {
        FactType factType = new FactType(FACT_NAME);
        factType.addRequiredField(FactFieldType.stringType(), FACT_TEXT);
        return factType;
    }

    private static Reader runTomita(String inputFile, Grammar grammar,
            FactType factType) {
        TomitaRunner tomita = new TomitaRunner();
        tomita.add(factType);
        tomita.add(grammar);
        tomita.setInputFile(inputFile);
        // TODO убрать
        tomita.setOutputFile("/home/nikit/tmp/tomita-work/out.xml");

        return tomita.run();
    }
}
