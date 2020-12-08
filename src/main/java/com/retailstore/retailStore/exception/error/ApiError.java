package com.retailstore.retailStore.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class ApiError {
    @JsonProperty("error")
    @NotNull
    private String errorCode;
    @JsonProperty("error_description")
    @Nullable
    private String errorDescriptions;

    public ApiError() {
    }

    public ApiError(@NotNull String errorCode, @Nullable String errorDescriptions) {
        this.errorCode = errorCode;
        this.errorDescriptions = errorDescriptions;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Nullable
    public String getErrorDescriptions() {
        return errorDescriptions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiError apiError = (ApiError) o;
        return Objects.equals(errorCode, apiError.errorCode) &&
                Objects.equals(errorDescriptions, apiError.errorDescriptions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorCode, errorDescriptions);
    }
}
