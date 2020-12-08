package com.retailstore.retailStore.exception;

import org.springframework.lang.Nullable;

public class ValidationException extends RuntimeException {
    @Nullable
    private String description;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, String description) {
        super(message);
        this.description = description;
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    @Nullable
    public String getDescription() {
        return description;
    }
}
