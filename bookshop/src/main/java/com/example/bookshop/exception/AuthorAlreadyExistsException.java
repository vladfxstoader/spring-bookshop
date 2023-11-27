package com.example.bookshop.exception;

public class AuthorAlreadyExistsException extends  RuntimeException{
    public AuthorAlreadyExistsException(String message) {
        super(message);
    }
}
