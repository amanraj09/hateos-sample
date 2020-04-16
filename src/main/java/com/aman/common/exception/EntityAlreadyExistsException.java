package com.aman.common.exception;

import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException {
    public EntityAlreadyExistsException(String apiResponseMessage, String systemMessage, Object... args) {
        super(apiResponseMessage, systemMessage, args);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
