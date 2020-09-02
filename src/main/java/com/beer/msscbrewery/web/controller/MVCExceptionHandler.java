package com.beer.msscbrewery.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.net.BindException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class MVCExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors =  new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : "+constraintViolation.getMessage());
        });
        return new ResponseEntity<>(errors , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<List> handleBindException(BindException ex){
       return new ResponseEntity(ex.getCause() , HttpStatus.BAD_REQUEST);
    }


    /**
     * This works fine from Controller class and 403 gets returned as
     * expected while testing via postman.
     * Other error codes highlighted above does not work with Controller errors
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMismatchException(MethodArgumentNotValidException ex){
        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
