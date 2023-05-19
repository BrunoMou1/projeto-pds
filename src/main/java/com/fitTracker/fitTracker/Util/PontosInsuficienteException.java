package com.fitTracker.fitTracker.Util;

public class PontosInsuficienteException extends RuntimeException{

    public PontosInsuficienteException(String message){
        super(message);
    }
    public PontosInsuficienteException(Throwable t){
        super(t);
    }
}
