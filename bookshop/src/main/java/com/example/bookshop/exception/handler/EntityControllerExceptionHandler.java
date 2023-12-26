package com.example.bookshop.exception.handler;

import com.example.bookshop.exception.EntityAlreadyExistsException;
import com.example.bookshop.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityControllerExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, EntityAlreadyExistsException.class})
    public ResponseEntity<String> methodArgumentExceptionHandling(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> entityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
