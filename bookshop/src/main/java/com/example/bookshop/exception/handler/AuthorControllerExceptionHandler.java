package com.example.bookshop.exception.handler;

import com.example.bookshop.exception.AuthorAlreadyExistsException;
import com.example.bookshop.exception.AuthorNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthorControllerExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, AuthorAlreadyExistsException.class})
    public ResponseEntity<String> methodArgumentExceptionHandling(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthorNotFoundException.class})
    public ResponseEntity<String> authorNotFound(AuthorNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
