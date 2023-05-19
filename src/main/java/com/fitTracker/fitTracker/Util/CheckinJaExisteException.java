package com.fitTracker.fitTracker.Util;

import org.springframework.http.HttpStatus;

public class CheckinJaExisteException extends RuntimeException{
    public CheckinJaExisteException(String message) {super(message);}

    public CheckinJaExisteException(Throwable t) {
        super(t);
    }

    public CheckinJaExisteException(HttpStatus conflict, String message) {
    }
}
