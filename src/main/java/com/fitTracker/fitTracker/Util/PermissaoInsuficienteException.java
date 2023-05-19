package com.fitTracker.fitTracker.Util;

public class PermissaoInsuficienteException extends RuntimeException {
    public PermissaoInsuficienteException(String message){
        super(message);
    }
    public PermissaoInsuficienteException(Throwable t){
        super(t);
    }
}
