package com.retailstore.retailStore.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.retailstore.retailStore.exception.ValidationException;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ErrorBody {
    @JsonProperty("errors")
    private List<ApiError> apiErrorList = new ArrayList<>();

    public ErrorBody() {
    }

    public ErrorBody(ApiError... apiErrors) {
        apiErrorList.addAll(Arrays.asList(apiErrors));
    }

    public ErrorBody(@NonNull List<ApiError> apiErrors) {
        apiErrorList.addAll(apiErrors);
    }

    public static ErrorBody from(BindingResult bindingResult) {
        List<ApiError> apiErrors = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            apiErrors.add(new ApiError(error.getDefaultMessage(),
                    "invalid " + error.getField()));
        }
        return new ErrorBody(apiErrors);
    }

    public static ErrorBody from(List<ValidationException> exceptions) {
        List<ApiError> apiErrors = new ArrayList<>();
        for (ValidationException exception : exceptions) {
            apiErrors.add(new ApiError(exception.getMessage(), exception.getDescription()));
        }
        return new ErrorBody(apiErrors);
    }

    public List<ApiError> getApiErrorList() {
        return apiErrorList;
    }

    private void setApiErrorList(List<ApiError> apiErrorList) {
        this.apiErrorList = apiErrorList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorBody errorBody = (ErrorBody) o;
        return Objects.equals(apiErrorList, errorBody.apiErrorList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apiErrorList);
    }
}
