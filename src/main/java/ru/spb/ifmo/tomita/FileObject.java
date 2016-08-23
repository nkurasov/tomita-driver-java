package ru.spb.ifmo.tomita;

import java.io.File;

/**
 * Объект, представляющий собой некоторый файл в файловой системе
 * 
 * @author nikit
 *
 */
public interface FileObject {

    /**
     * Записать данные объекта в файл в указанной директории
     * 
     * @param outDir
     *            директория для записи
     */
    void writeTo(File outDir);

    /**
     * @return имя файла (без суффикса)
     */
    String getName();

    /**
     * @return суффикс имени файла
     */
    String getSuffix();

    /**
     * @return имя файла (с суффиксом)
     */
    default String getFileName() {
        return getName() + "." + getSuffix();
    }
}
