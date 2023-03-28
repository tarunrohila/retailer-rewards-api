package com.demo.api.exception;

/**
 * Custom exception class to handle Transaction Exception
 *
 * @author Tarun Rohila
 * @since Mar 25, 2023
 */
public class TransactionException extends RuntimeException {

    private String message;

    /**
     * Constructs a new runtime exception with {@code null} as its detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public TransactionException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *     {@link #getMessage()} method.
     */
    public TransactionException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Constructs a new runtime exception with the specified detail message and cause.
     *
     * <p>Note that the detail message associated with {@code cause} is <i>not</i> automatically
     * incorporated in this runtime exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link
     *     #getMessage()} method).
     * @param cause the cause (which is saved for later retrieval by the {@link #getCause()}
     *     method). (A {@code null} value is permitted, and indicates that the cause is nonexistent
     *     or unknown.)
     * @since 1.4
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
