package com.s3training.jwt.generate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage dataNotFound(DataNotFoundException ex, WebRequest webRequest){
        return new ErrorMessage(HttpStatus.NOT_FOUND.value()
                , ex.getMessage()
                , new Date()
                , webRequest.getDescription(false));
    }

}
