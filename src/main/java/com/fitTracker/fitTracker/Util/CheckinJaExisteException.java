package com.fitTracker.fitTracker.Util;

public class CheckinJaExisteException extends RuntimeException{
    public CheckinJaExisteException(String message) {
        super(message);
    }

    public CheckinJaExisteException(Throwable t) {
        super(t);
    }
}
