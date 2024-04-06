package com.eshop.demo.controller;

import com.eshop.demo.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleException(UserAlreadyExists userAlreadyExists){
        return new ResponseEntity<>(userAlreadyExists.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(UserNotVerifiedException userNotVerifiedException){
        return new ResponseEntity<>(userNotVerifiedException.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(ProductNotFound productNotFound){
        return new ResponseEntity<>(productNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(CategoryNotFound categoryNotFound){
        return new ResponseEntity<>(categoryNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(UserNotFound userNotFound){
        return new ResponseEntity<>(userNotFound.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(IllegalArgumentException IllegalArgumentException){
        return new ResponseEntity<>(IllegalArgumentException.getMessage(), HttpStatus.NOT_FOUND);
    }

}
