package ru.spb.ifmo.tomita;

/**
 * Исключение, генерируемое при ошибках в работе парсера tomita
 * 
 * @author nikit
 *
 */
public class TomitaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public TomitaException() {
        super();
    }

    /**
     * @param message
     *            сообщение об ошибке
     * @param cause
     */
    public TomitaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     *            сообщение об ошибке
     */
    public TomitaException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public TomitaException(Throwable cause) {
        super(cause);
    }

}
