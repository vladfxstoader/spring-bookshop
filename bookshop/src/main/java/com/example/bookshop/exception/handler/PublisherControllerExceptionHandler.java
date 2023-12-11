package com.example.bookshop.exception.handler;

import com.example.bookshop.exception.AuthorNotFoundException;
import com.example.bookshop.exception.PublisherAlreadyExistsException;
import com.example.bookshop.exception.PublisherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PublisherControllerExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, PublisherAlreadyExistsException.class})
    public ResponseEntity<String> methodArgumentExceptionHandling(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PublisherNotFoundException.class})
    public ResponseEntity<String> publisherNotFound(PublisherNotFoundException ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
