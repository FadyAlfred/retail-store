package com.retailstore.retailStore.exception;

import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;

import javax.validation.ValidationException;
import java.util.List;

public class RequestInputValidationException extends RuntimeException {

    @Nullable
    private BindingResult bindingResult;

    @Nullable
    private List<ValidationException> exceptions;

    public RequestInputValidationException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }

    public RequestInputValidationException(List<ValidationException> exceptions) {
        this.exceptions = exceptions;
    }

    public RequestInputValidationException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }

    public RequestInputValidationException(String message, Throwable cause, BindingResult bindingResult) {
        super(message, cause);
        this.bindingResult = bindingResult;
    }

    public RequestInputValidationException(String message, @Nullable List<ValidationException> exceptions) {
        super(message);
        this.exceptions = exceptions;
    }

    public RequestInputValidationException(String message, Throwable cause, @Nullable List<ValidationException> exceptions) {
        super(message, cause);
        this.exceptions = exceptions;
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

    @Nullable
    public List<ValidationException> getExceptions() {
        return exceptions;
    }
}
