package com.aman.security.exception;

import org.springframework.security.core.AuthenticationException;

public class SecurityException extends AuthenticationException {
    public SecurityException(String msg, Throwable t) {
        super(msg, t);
    }
}
