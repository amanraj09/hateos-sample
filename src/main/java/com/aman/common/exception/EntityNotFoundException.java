package com.aman.common.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends BaseException {
    public EntityNotFoundException(String apiResponseMessage, String systemMessage, Object... args) {
        super(apiResponseMessage, systemMessage, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
