package com.uss.mars.exploration.exceptions;

public class InvalidSyntaxException extends RuntimeException {

    /**
     * Constructs an <code>InvalidSyntaxException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public InvalidSyntaxException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>InvalidSyntaxException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public InvalidSyntaxException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an <code>InvalidSyntaxException</code> with the root cause.
     *
     * @param cause root cause
     */
    public InvalidSyntaxException(Throwable cause) {
        super(cause);
    }

}
