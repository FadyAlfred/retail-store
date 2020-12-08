package com.retailstore.retailStore.exception;

import com.retailstore.retailStore.exception.error.ApiError;
import com.retailstore.retailStore.exception.error.ErrorBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RequestInputValidationException.class})
    public ResponseEntity<ErrorBody> handleInvalidMinAdsReserve(Exception exception, WebRequest request) {
        RequestInputValidationException requestInputValidationException = (RequestInputValidationException) exception;
        if (requestInputValidationException.getBindingResult() != null) {
            return ResponseEntity.badRequest().body(ErrorBody.from(requestInputValidationException.getBindingResult()));
        } else if (requestInputValidationException.getExceptions() != null) {
            return ResponseEntity.badRequest().body(ErrorBody.from((BindingResult) requestInputValidationException.getExceptions()));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBody(new ApiError("ValidationException", null)));
        }
    }

    @ExceptionHandler({CriticalException.class})
    public ResponseEntity<ErrorBody> handleCriticalException(Exception exception, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorBody(new ApiError(exception.getMessage(), "Required Data was not inserted in database")));
    }
}
