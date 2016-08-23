package ru.spb.ifmo.tomita;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import ru.spb.ifmo.fact.util.CheckUtil;
import ru.spb.ifmo.tomita.dictionary.UserDictionary;
import ru.spb.ifmo.tomita.dictionary.grammar.Grammar;

/**
 * Парсер фактов tomita. Представляет из себя обёртку над парсером tomita,
 * который поставляется в виде готовых утилит для различных платформ.
 * Настраивает рабочее окружение для парсера и запускает его отдельным
 * процессом. Рабочее окружение представляет из себя {@link TomitaConfigurer
 * конфигурационный файл} и файлы {@link UserDictionary словарей} и
 * {@link Grammar грамматик}. Результаты работы парсера записываются в файл,
 * указанный в {@link TomitaConfigurer конфигурации}
 * 
 * @author nikit
 *
 */
public class Tomita {

    private final TomitaConfigurer config;

    private final Collection<UserDictionary<?>> dictionaries;

    private final Collection<Grammar> grammars;

    /**
     * Конструирует новый экземпляр Tomita-парсера
     * 
     * @param config
     *            конфигурация парсера для запуска
     */
    public Tomita(TomitaConfigurer config) {
        CheckUtil.shouldNotNull(config, "Не задана конфигурация парсера");
        this.config = config;
        this.dictionaries = new ArrayList<UserDictionary<?>>();
        this.grammars = new ArrayList<>();
    }

    /**
     * @return конфигурация парсера
     */
    public TomitaConfigurer getConfig() {
        return config;
    }

    /**
     * Добавить словарь
     * 
     * @param dictionary
     *            пользовательский словарь
     */
    public void add(UserDictionary<?> dictionary) {
        if (dictionary != null) {
            dictionaries.add(dictionary);
        }
    }

    /**
     * Добавить грамматику
     * 
     * @param grammar
     *            грамматика
     */
    public void add(Grammar grammar) {
        if (grammar != null) {
            grammars.add(grammar);
        }
    }

    /**
     * Подготовить все файлы перед запуском парсера
     * 
     * @param outDir
     *            рабочий каталог
     */
    protected void prepareRun(File outDir) {
        Collection<FileObject> fileObjects = new ArrayList<>();
        fileObjects.add(config);
        fileObjects.addAll(dictionaries);
        fileObjects.addAll(grammars);
        for (FileObject fileObject : fileObjects) {
            fileObject.writeTo(outDir);
        }
    }

    /**
     * Запуск парсера с заданными настройками
     * 
     * @param workDirPath
     *            путь к рабочему каталогу. В нём размещаются файлы для парсера
     *            (конфигурационный файл, словари и грамматики)
     */
    public void run(String workDirPath) {
        CheckUtil.shouldNotNull(workDirPath,
                "Не задан путь к рабочей директории");
        run(new File(workDirPath));
    }

    /**
     * Запуск парсера с заданными настройками
     * 
     * @param workDirPath
     *            рабочий каталог. В нём размещаются файлы для парсера
     *            (конфигурационный файл, словари и грамматики)
     */
    public void run(File workDir) {
        // если переданный экземпляр File не является директорией - ничего не
        // делать
        if (!workDir.isDirectory()) {
            return;
        }

        // записываем файлы в рабочий каталог
        prepareRun(workDir);

        try {
            // запуск парсера в отдельном процессе и ожидание его завершения
            new ProcessBuilder("tomita", config.getFileName())
                    .directory(workDir).inheritIO().start().waitFor();
        } catch (IOException e) {
            throw new IllegalStateException("Ошибка при запуске tomita", e);
        } catch (InterruptedException e) {
            throw new IllegalStateException("Ошибка в работе tomita", e);
        }
    }
}
