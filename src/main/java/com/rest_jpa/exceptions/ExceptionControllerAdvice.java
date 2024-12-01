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
    public ResponseEntity<ErrorRestResponse> handleExceptions(ApplicationException ex) {
        LOG.error("Exception in rest service", ex);
        ErrorRestResponse errorRestResponse = ErrorRestResponse.FAIL(ex.getErrorKey().name(), ex.getParameters(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorRestResponse, HttpStatus.OK);
    }

    @ExceptionHandler({FacadeException.class})
    public ResponseEntity<ErrorRestResponse> handleExceptions(FacadeException ex) {
        LOG.error("Exception in rest service", ex);
        if (ex.getCause() != null && ex.getCause() instanceof ApplicationException) {
            ErrorRestResponse errorRestResponse = ErrorRestResponse.FAIL(ex.getKey(), ex.getParameters(), ex.getLocalizedMessage());
            return new ResponseEntity<>(errorRestResponse, HttpStatus.OK);
        } else {
            ErrorRestResponse errorRestResponse = new ErrorRestResponse(ex.getLocalizedMessage());
            return new ResponseEntity<>(errorRestResponse, HttpStatus.OK);
        }
    }
//    @ExceptionHandler({MethodArgumentNotValidException.class})
//    public ResponseEntity<ErrorRestResponse> handleExceptions(MethodArgumentNotValidException ex) {
//        LOG.error("Exception in rest service", ex);
//        ErrorRestResponse restResponse = ErrorRestResponse.FAIL(ex.getMessage(), ex.getBindingResult().getAllErrors().stream().map(e -> e.), ex.getLocalizedMessage());
////        TODO переписать логику фронта для обработки нескольких ошибок, а тут собрать массив ошибок
//        return new ResponseEntity<>(restResponse, HttpStatus.OK);
//    }

    @ExceptionHandler({TokenRefreshException.class, JwtAuthenticationException.class})
    public ResponseEntity<ErrorRestResponse> handleAuthenticateException(TokenRefreshException ex) {
        LOG.error("Exception in rest service", ex);
        ErrorRestResponse errorRestResponse = ErrorRestResponse.FAIL(ex.getKey(), ex.getParameters(), ex.getLocalizedMessage());
        return new ResponseEntity<>(errorRestResponse, HttpStatus.FORBIDDEN);
    }
}
