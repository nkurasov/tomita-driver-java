package ru.spb.ifmo.tomita;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

/**
 * Работа с файлами
 * 
 * @author nikit
 *
 */
public final class FileUtil {

    private FileUtil() {
        // do nothing
    }

    /**
     * Записать данные в файл
     * 
     * @param outFile
     *            файл для записи
     * @param source
     *            данные для записи
     */
    public static void writeToFile(File outFile, String source) {
        writeTo(outFile, new StringReader(source));
    }

    public static void writeTo(File outFile, Reader source) {
        createNewFile(outFile);
        try (BufferedReader in = new BufferedReader(source)) {
            try (Writer out = new FileWriter(outFile)) {
                in.lines().forEach(line -> appendTo(out, line));
                out.flush();
            }
        } catch (IOException e) {
            throw new FileException("Ошибка при записи данных в файл", e);
        }
    }

    private static void appendTo(Writer out, String line) {
        try {
            out.append(line).append('\n');
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    /**
     * Создать новый файл
     * 
     * @param outFile
     *            описание файла
     * @throws FileException
     *             если при создании файла возникла ошибка
     */
    private static void createNewFile(File outFile) {
        try {
            outFile.createNewFile();
        } catch (IOException e) {
            throw new FileException("Ошибка при создании нового файла", e);
        }
    }

    /**
     * Обернуть строку двойными кавычками с начала и конца
     * 
     * @param line
     *            исходная строка
     * @return строка в кавычках
     */
    public static String toStringLiteral(String line) {
        return "\"" + line + "\"";
    }

    public static File createTempFile(String prefix, String suffix) {
        try {
            return File.createTempFile(prefix, suffix);
        } catch (IOException e) {
            throw new FileException("Ошибка при создании файла", e);
        }

    }
}
