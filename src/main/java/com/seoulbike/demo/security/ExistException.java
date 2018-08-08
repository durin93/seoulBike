package com.seoulbike.demo.security;

public class ExistException extends  RuntimeException{

    public ExistException() {
    }

    public ExistException(String message) {
        super(message);
    }
}
