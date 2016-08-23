package ru.spb.ifmo.tomita.dictionary.grammar;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.FileObject;
import ru.spb.ifmo.tomita.FileUtil;

/**
 * Грамматика
 * 
 * @author nikit
 *
 */
public class Grammar implements FileObject {

    public static final String DEFAULT_SUFFIX = "cxx";

    private final String rootSymbol;

    private final Collection<Rule> rules;

    private final String name;

    private String suffix = DEFAULT_SUFFIX;

    /**
     * @param name
     *            имя
     * @param rootSymbol
     *            корневой символ грамматики
     * @param rules
     *            набор правил грамматики
     */
    public Grammar(String name, String rootSymbol, Rule... rules) {
        this(name, rootSymbol, Arrays.asList(rules));
    }

    public Grammar(String name, String rootSymbol, Collection<Rule> rules) {
        CheckUtil.shouldNotNull(name, "Не задано имя грамматики");
        this.name = name;
        CheckUtil.shouldNotNull(rootSymbol,
                "Не задан корневой символ грамматики");
        this.rootSymbol = rootSymbol;
        CheckUtil.shouldNotEmpty(rules,
                "Грамматика должна иметь хотя бы одно правило");
        if (!rules.stream().anyMatch(
                r -> rootSymbol.equals(r.getNonTerminalId()))) {
            throw new IllegalStateException(
                    "В наборе правил не определяется нетерминального символа с таким названием ("
                            + rootSymbol + ")");
        }

        this.rules = Collections.unmodifiableCollection(rules);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix
     *            суффикс
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    /**
     * @return корневой символ грамматики
     */
    public String getRootSymbol() {
        return rootSymbol;
    }

    /**
     * @return набор правил грамматики
     */
    public Collection<Rule> getRules() {
        return Collections.unmodifiableCollection(rules);
    }

    @Override
    public void writeTo(File outDir) {
        File outFile = new File(outDir, getFileName());
        FileUtil.writeToFile(outFile, toString());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("#encoding \"utf-8\"\n");
        builder.append("#GRAMMAR_ROOT ").append(rootSymbol).append('\n');
        for (Rule rule : rules) {
            builder.append(rule.formatString()).append(';').append('\n');
        }
        return builder.toString();
    }
}
