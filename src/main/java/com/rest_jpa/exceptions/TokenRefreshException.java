package com.rest_jpa.exceptions;

public class TokenRefreshException extends ApplicationException{
    private static final long serialVersionUID = 1L;


    public TokenRefreshException(ErrorKey key, Object... parameters) {
        super(key, parameters);
    }

    public TokenRefreshException(ErrorKey key, String param1) {
        super(key, param1);
    }

    public TokenRefreshException(ErrorKey key) {
        super(key);
    }

    public TokenRefreshException(Throwable t, ErrorKey key, Object... parameters) {
        super(t, key, parameters);
    }

    public TokenRefreshException(Throwable t, ErrorKey key, String param1) {
        super(t, key, param1);
    }

    public TokenRefreshException(Throwable t, ErrorKey key) {
        super(t, key);
    }
}
