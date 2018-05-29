package com.rest_jpa.exceptions;

public class RestResponse {

    private ErrorKey error;
    private String message;

    public RestResponse(ErrorKey error) {
        this.error = error;
    }

    public RestResponse(ErrorKey error, String message) {
        this.error = error;
        this.message = message;
    }

    public ErrorKey getError() {
        return error;
    }

    public void setError(ErrorKey error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
