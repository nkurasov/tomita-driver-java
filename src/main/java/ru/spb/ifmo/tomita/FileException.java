package ru.spb.ifmo.tomita;

/**
 * Исключение при работе с файлами
 * 
 * @author nikit
 *
 */
public class FileException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public FileException() {
        super();
    }

    /**
     * @param message
     *            сообщение об ошибке
     * @param cause
     */
    public FileException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     *            сообщение об ошибке
     */
    public FileException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public FileException(Throwable cause) {
        super(cause);
    }
}
