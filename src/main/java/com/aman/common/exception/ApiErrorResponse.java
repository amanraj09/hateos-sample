package com.aman.common.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private String message;
    private Integer errorCode;

    public ApiErrorResponse(Integer errorCode, String message) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
