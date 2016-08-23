package ru.spb.ifmo.tomita;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.UserDictionary;
import ru.spb.ifmo.tomita.dictionary.article.Article;
import ru.spb.ifmo.tomita.dictionary.article.GrammarArticle;
import ru.spb.ifmo.tomita.dictionary.fact.FactType;
import ru.spb.ifmo.tomita.dictionary.grammar.Grammar;

/**
 * Конфигурация парсера Tomita. Описывает содержимое конфигурационного файла
 * 
 * @author nikit
 *
 */
public class TomitaConfigurer implements FileObject {

    private static final String SUFFIX = "proto";

    private String inputFile;

    private String outputFile;

    private OutputFormat outputFormat;

    private UserDictionary<Article> rootDictionary;

    private final Collection<Article> articles = new ArrayList<>();

    private final Collection<FactType> factTypes = new ArrayList<>();

    private final String name;

    /**
     * Конструирует экземпляр объекта с заданными параметрами
     * 
     * @param name
     *            имя конфигурационного файла
     */
    public TomitaConfigurer(String name) {
        CheckUtil.shouldNotNull(name, "Не задано имя");
        this.name = name;
    }

    @Override
    public void writeTo(File outDir) {
        File outFile = new File(outDir, getFileName());
        FileUtil.writeToFile(outFile, toString());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSuffix() {
        return SUFFIX;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("encoding \"utf-8\";\n");
        result.append("TTextMinerConfig {\n").append("Dictionary = ")
                .append(literal(rootDictionary.getFileName())).append('\n');
        result.append("Input = { File = ").append(literal(inputFile))
                .append(" }\n");
        result.append("Output = { File = ").append(literal(outputFile))
                .append(" Format = ").append(outputFormat.getTypeName())
                .append(" }\n");
        result.append("Articles = [ ")
                .append(String.join(", ", toStringArticles())).append(" ]\n");
        result.append("Facts = [ ")
                .append(String.join(", ", toStringFactTypes())).append(" ]\n");
        result.append("}");
        return result.toString();
    }

    private Collection<String> toStringArticles() {
        return articles.stream().map(a -> a.getName())
                .map(n -> "{ Name = \"" + n + "\" }")
                .collect(Collectors.toList());
    }

    private Collection<String> toStringFactTypes() {
        return factTypes.stream().map(f -> f.getName())
                .map(n -> "{ Name = \"" + n + "\" }")
                .collect(Collectors.toList());
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    public String getOutputFile() {
        return outputFile;
    }

    public Collection<FactType> getFactTypes() {
        return Collections.unmodifiableCollection(factTypes);
    }

    public Collection<Article> getArticle() {
        return Collections.unmodifiableCollection(articles);
    }

    public Collection<Grammar> getGrammars() {
        return articles.stream().filter(GrammarArticle.class::isInstance)
                .map(GrammarArticle.class::cast)
                .map(GrammarArticle::getGrammar).collect(Collectors.toList());
    }

    public void add(Article article) {
        if (article != null) {
            articles.add(article);
        }
    }

    public void add(FactType factType) {
        if (factType != null) {
            factTypes.add(factType);
        }
    }

    public OutputFormat getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(OutputFormat outputFormat) {
        this.outputFormat = outputFormat;
    }

    public UserDictionary<Article> getRootDictionary() {
        return rootDictionary;
    }

    public void setRootDictionary(UserDictionary<Article> rootDictionary) {
        this.rootDictionary = rootDictionary;
    }

    private static String literal(String line) {
        return FileUtil.toStringLiteral(line);
    }
}
