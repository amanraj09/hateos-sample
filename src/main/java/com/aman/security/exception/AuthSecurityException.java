package com.aman.security.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthSecurityException extends AuthenticationException {
    public AuthSecurityException(String msg, Throwable t) {
        super(msg, t);
    }
}
