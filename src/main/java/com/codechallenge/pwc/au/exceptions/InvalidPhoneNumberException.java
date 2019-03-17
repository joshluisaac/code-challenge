package com.codechallenge.pwc.au.exceptions;

public class InvalidPhoneNumberException extends RuntimeException {

    /**
     * Constructs an <code>InvalidPhoneNumberException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public InvalidPhoneNumberException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>InvalidPhoneNumberException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public InvalidPhoneNumberException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an <code>InvalidPhoneNumberException</code> with the root cause.
     *
     * @param cause root cause
     */
    public InvalidPhoneNumberException(Throwable cause) {
        super(cause);
    }
}
