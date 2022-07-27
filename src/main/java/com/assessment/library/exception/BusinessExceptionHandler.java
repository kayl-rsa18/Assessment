package com.assessment.library.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.assessment.library.model.ErrorDetails;

@ControllerAdvice
public class BusinessExceptionHandler {
	
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),request.getDescription(false), new Date(), HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationException(MethodArgumentNotValidException ex, WebRequest request) {
         ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),request.getDescription(false), new Date(), HttpStatus.BAD_REQUEST);
         return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),request.getDescription(false), new Date(), HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
