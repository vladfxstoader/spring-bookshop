package com.example.bookshop.exception;

public class PublisherAlreadyExistsException extends RuntimeException {
    public PublisherAlreadyExistsException(String message) {
        super(message);
    }
}
