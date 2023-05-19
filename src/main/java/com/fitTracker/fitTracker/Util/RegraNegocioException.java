package com.fitTracker.fitTracker.Util;

public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(String message) {
        super(message);
    }

    public RegraNegocioException(Throwable t) {
        super(t);
    }
}
