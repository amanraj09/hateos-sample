package com.aman.common.exception;

import java.util.Map;

public class ApiValidationErrorResponse extends ApiErrorResponse {
    private Map<String, String> errors;

    public ApiValidationErrorResponse(Integer status, Map<String, String> validationErrors) {
        super(status, validationErrors.values().stream().findFirst().orElse(null));
        this.errors = validationErrors;
    }
}
