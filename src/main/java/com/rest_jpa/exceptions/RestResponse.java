package com.rest_jpa.exceptions;

import java.util.Arrays;

public class RestResponse {

    private String error;
    private String errorMessage;
    private Object[] errorParams;

    public static RestResponse FAIL(String error, Object... errorParams){
        return new RestResponse(error,errorParams);
    }

    public static RestResponse FAIL(String error, Object[] errorParams, String errorMessage) {
        RestResponse response = new RestResponse(error, errorParams);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public RestResponse() {
    }

    public RestResponse(String error) {
        this.error = error;
    }

    public RestResponse(String error, Object[] errorParams) {
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
        return "RestResponse{" +
                "error='" + error + '\'' +
                ", errorParams=" + Arrays.toString(errorParams) +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
