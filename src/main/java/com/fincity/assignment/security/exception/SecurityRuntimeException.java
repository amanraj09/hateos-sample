package com.fincity.assignment.security.exception;

import org.springframework.security.core.AuthenticationException;

public class SecurityRuntimeException extends AuthenticationException {
    public SecurityRuntimeException(String msg, Throwable t) {
        super(msg, t);
    }
}
