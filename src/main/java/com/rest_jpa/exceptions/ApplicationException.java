package com.rest_jpa.exceptions;

import java.util.Arrays;
import java.util.List;

public class ApplicationException extends Exception {

    private ErrorKey errorKey;
    private String message;
    private List<Object> errorParameters;

    public ApplicationException(ErrorKey errorKey, List<Object> errorParameters) {
        super();
        this.errorKey = errorKey;
        this.errorParameters = errorParameters;
    }

    public ApplicationException(ErrorKey errorKey, String message, Object... errorParameters) {
        super();
        this.errorKey = errorKey;
        this.message = message;
        this.errorParameters = Arrays.asList(errorParameters);
    }

    public ErrorKey getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(ErrorKey errorKey) {
        this.errorKey = errorKey;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getErrorParameters() {
        return errorParameters;
    }

    public void setErrorParameters(List<Object> errorParameters) {
        this.errorParameters = errorParameters;
    }
}
