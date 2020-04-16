package com.aman.common.exception;

import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public abstract class BaseException extends Exception {
    private String apiResponseMessage;

    public BaseException(String apiResponseMessage, String systemMessage, Object... args) {
        super(args.length == 0 ? systemMessage : MessageFormat.format(systemMessage, args));
        this.apiResponseMessage = apiResponseMessage;
    }

    public abstract HttpStatus getHttpStatus();

    public String getApiResponseMessage() {
        return this.apiResponseMessage;
    }
}
