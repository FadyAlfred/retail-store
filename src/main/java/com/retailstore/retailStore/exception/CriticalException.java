package com.retailstore.retailStore.exception;

public class CriticalException extends RuntimeException {
    public CriticalException() {
        super("SomethingBadHappened");
    }

    public CriticalException(String message) {
        super(message);
    }

    public CriticalException(String message, Throwable cause) {
        super(message, cause);
    }
}
