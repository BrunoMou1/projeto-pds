package com.fitTracker.fitTracker.Util;

public class ElementoExisteException extends RuntimeException{

    public ElementoExisteException(String message) {
        super(message);
    }

    public ElementoExisteException(Throwable t) {
        super(t);
    }
}
