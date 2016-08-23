package ru.spb.ifmo.tomita;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import ru.spb.ifmo.tomita.dictionary.Dictionaries;
import ru.spb.ifmo.tomita.dictionary.UserDictionary;
import ru.spb.ifmo.tomita.dictionary.article.Article;
import ru.spb.ifmo.tomita.dictionary.article.GrammarArticle;
import ru.spb.ifmo.tomita.dictionary.fact.FactType;
import ru.spb.ifmo.tomita.dictionary.grammar.Grammar;

import com.google.common.io.Files;

/**
 * Обёртка над {@link Tomita парсером}, автоматизирующая большую часть работы по
 * настройке парсера перед запуском. Данный объект автоматически формирует
 * список файлов, необходимых для запуска и создает временный каталог для
 * размещения файлов парсера. При минимальной настройке томиты требуется указать
 * только набор {@link Grammar грамматик} и {@link FactType типов фактов},
 * необходимых для поиска
 * 
 * @author nikit
 *
 */
public class TomitaRunner {

    public static final String DEFAULT_INPUT_FILE = "input.txt";

    public static final String DEFAULT_OUTPUT_FILE = "facts.out";

    public static final String DEFAULT_CONFIG_NAME = "config";

    public static final String DEFAULT_GAZETTEER_NAME = "rootGazetteer";

    public static final String DEFAULT_FACTS_NAME = "facts";

    private final TomitaConfigurer config;

    private final UserDictionary<Article> gazetteer;

    private final UserDictionary<FactType> factTypes;

    /**
     * Создает экземпляр конфигурации парсера с настройками по умолчанию
     */
    public TomitaRunner() {
        gazetteer = Dictionaries.createGazetteer(DEFAULT_GAZETTEER_NAME);
        factTypes = Dictionaries.createFactTypesDictionary(DEFAULT_FACTS_NAME);
        gazetteer.importDictionary(factTypes);

        // set default options to tomita configuration file
        config = new TomitaConfigurer(DEFAULT_CONFIG_NAME);
        config.setInputFile(DEFAULT_INPUT_FILE);
        config.setOutputFile(DEFAULT_OUTPUT_FILE);
        config.setOutputFormat(OutputFormat.XML);
        config.setRootDictionary(gazetteer);
    }

    /**
     * Запуск парсера с заданными параметрами
     * 
     * @return поток чтения из файла результатов
     */
    public Reader run() {
        Tomita tomita = new Tomita(config);

        for (Grammar grammar : config.getGrammars()) {
            tomita.add(grammar);
        }
        tomita.add(factTypes);
        tomita.add(gazetteer);

        File workDirectory = createWorkDirectory();
        tomita.run(workDirectory);

        File resultsFile = getOutputFile(workDirectory);
        try {
            return new FileReader(resultsFile);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Файл результатов ("
                    + resultsFile.getAbsolutePath() + ") не найден", e);
        }
    }

    /**
     * Возвращает экземпляр {@link File}, преставляющего файл с результатами
     * работы парсера
     * 
     * @param workDir
     *            рабочий каталог
     * @return если был указан относительный путь файла - возвращает объект
     *         {@link File} относительно рабочего каталога, если указан
     *         абсолютный путь - возвращает объект по этому пути
     */
    private File getOutputFile(File workDir) {
        File result = new File(workDir, config.getOutputFile());
        if (result.exists()) {
            return result;
        } else {
            return new File(config.getOutputFile());
        }
    }

    /**
     * Добавить грамматику
     * 
     * @param grammar
     *            грамматика
     */
    public void add(Grammar grammar) {
        GrammarArticle article = new GrammarArticle(grammar);
        config.add(article);
        gazetteer.addObject(article);
    }

    /**
     * Добавить тип факта
     * 
     * @param factType
     *            тип факта
     */
    public void add(FactType factType) {
        config.add(factType);
        factTypes.addObject(factType);
    }

    /**
     * Задать формат вывода результатов
     * 
     * @param format
     *            формат вывода результатов
     */
    public void setOutputFormat(OutputFormat format) {
        config.setOutputFormat(format);
    }

    /**
     * Задать путь к файлу с исходным текстом
     * 
     * @param inputFile
     *            файл с исходными данными
     */
    public void setInputFile(String inputFile) {
        config.setInputFile(inputFile);
    }

    /**
     * Задать путь к файлу результатов
     * 
     * @param outputFile
     *            путь к файлу для записи результатов
     */
    public void setOutputFile(String outputFile) {
        config.setOutputFile(outputFile);
    }

    /**
     * @return путь к файлу с исходными данными
     */
    public String getInputFile() {
        return config.getInputFile();
    }

    /**
     * @return путь к файлу для записи результатов поиска
     */
    public String getOutputFile() {
        return config.getOutputFile();
    }

    /**
     * @return формат вывода результатов поиска
     */
    public OutputFormat getOutputFormat() {
        return config.getOutputFormat();
    }

    private static File createWorkDirectory() {
        return Files.createTempDir();
    }
}
