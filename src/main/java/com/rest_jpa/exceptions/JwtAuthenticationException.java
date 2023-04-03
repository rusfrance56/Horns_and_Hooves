package com.rest_jpa.exceptions;

public class JwtAuthenticationException extends ApplicationException {
    public JwtAuthenticationException(ErrorKey key, Object... parameters) {
        super(key, parameters);
    }

    public JwtAuthenticationException(ErrorKey key, String param1) {
        super(key, param1);
    }

    public JwtAuthenticationException(ErrorKey key) {
        super(key);
    }

    public JwtAuthenticationException(Throwable t, ErrorKey key, Object... parameters) {
        super(t, key, parameters);
    }

    public JwtAuthenticationException(Throwable t, ErrorKey key, String param1) {
        super(t, key, param1);
    }

    public JwtAuthenticationException(Throwable t, ErrorKey key) {
        super(t, key);
    }
}
