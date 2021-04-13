package com.s3training.jwt.generate.exception;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}
