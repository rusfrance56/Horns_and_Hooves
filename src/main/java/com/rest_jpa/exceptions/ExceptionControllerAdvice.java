package com.rest_jpa.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler({ApplicationException.class})
    public ResponseEntity<RestResponse> handleExceptions(ApplicationException ex) {
        LOG.error("Exception in rest service", ex);
        RestResponse restResponse = RestResponse.FAIL(ex.getErrorKey().name(), ex.getParameters(), ex.getLocalizedMessage());
        return new ResponseEntity<>(restResponse, HttpStatus.OK);
    }
    @ExceptionHandler({FacadeException.class})
    public ResponseEntity<RestResponse> handleExceptions(FacadeException ex) {
        LOG.error("Exception in rest service", ex);
        if (ex.getCause() != null && ex.getCause() instanceof ApplicationException) {
            RestResponse restResponse = RestResponse.FAIL(ex.getKey(), ex.getParameters(), ex.getLocalizedMessage());
            return new ResponseEntity<>(restResponse, HttpStatus.OK);
        } else {
            RestResponse restResponse = new RestResponse(ex.getLocalizedMessage());
            return new ResponseEntity<>(restResponse, HttpStatus.OK);
        }
    }
}
