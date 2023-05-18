package com.fitTracker.fitTracker.Util;

public class RecompensaEsgotadaException extends RuntimeException{

    public RecompensaEsgotadaException(String message){
        super(message);
    }
    public RecompensaEsgotadaException(Throwable t){
        super(t);
    }
}
