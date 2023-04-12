package com.fitTracker.fitTracker.Util;

public class ElementoNaoEncontradoException extends RuntimeException{

    public ElementoNaoEncontradoException(String message) {
        super(message);
    }

    public ElementoNaoEncontradoException(Throwable t) {
        super(t);
    }

}
