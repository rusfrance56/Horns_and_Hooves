package com.rest_jpa.exceptions;

import java.util.Arrays;

public class ErrorRestResponse {

    private String error;
    private String errorMessage;
    private Object[] errorParams;

    public static ErrorRestResponse FAIL(String error, Object... errorParams){
        return new ErrorRestResponse(error,errorParams);
    }

    public static ErrorRestResponse FAIL(String error, Object[] errorParams, String errorMessage) {
        ErrorRestResponse response = new ErrorRestResponse(error, errorParams);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public ErrorRestResponse() {
    }

    public ErrorRestResponse(String error) {
        this.error = error;
    }

    public ErrorRestResponse(String error, Object[] errorParams) {
        this.error = error;
        this.errorParams = errorParams;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object[] getErrorParams() {
        return errorParams;
    }

    public void setErrorParams(Object[] errorParams) {
        this.errorParams = errorParams;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "ErrorRestResponse{" +
                "error='" + error + '\'' +
                ", errorParams=" + Arrays.toString(errorParams) +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
