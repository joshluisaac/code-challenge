package com.uss.mars.exploration.exceptions;

public class CoordinateOutsideBoundsException extends RuntimeException {

    /**
     * Constructs an <code>CoordinateOutsideBoundsException</code> with the specified message.
     *
     * @param msg the detail message
     */
    public CoordinateOutsideBoundsException(String msg) {
        super(msg);
    }

    /**
     * Constructs an <code>CoordinateOutsideBoundsException</code> with the specified message and
     * root cause.
     *
     * @param msg the detail message
     * @param t root cause
     */
    public CoordinateOutsideBoundsException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an <code>CoordinateOutsideBoundsException</code> with the root cause.
     *
     * @param t root cause
     */
    public CoordinateOutsideBoundsException(Throwable cause) {
        super(cause);
    }



}
