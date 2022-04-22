package com.gamification.api;

/**
 * @author marco.sciacovelli
 * Exception thrown when a task fails to execute.
 */
public class FailedExecutionException extends Exception {

    /**
     * Constructs a new exception with {@code null} as its detail message.
     */
    public FailedExecutionException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message, cause, if suppression should be enabled
     * and if the Stacktrace should be written.
     * @param message the detail message.
     * @param cause the cause.
     * @param enableSuppression whether suppression is enabled or disabled
     * @param writableStackTrace whether the stack trace should be writable
     */
    public FailedExecutionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    /**
     * Constructs a new exception with the specified detail message and the cause.
     * @param message the detail message.
     * @param cause the cause.
     */
    public FailedExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message.
     */
    public FailedExecutionException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause.
     */
    public FailedExecutionException(Throwable cause) {
        super(cause);
    }
}
