package com.fitTracker.fitTracker.Util;

public class RepositoryNullException  extends RuntimeException{

    public RepositoryNullException(String message) {
        super(message);
    }

    public RepositoryNullException(Throwable t) {
        super(t);
    }
}
