package com.s3training.jwt.generate.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class ErrorMessage {
    private Integer statusCode;
    private String message;
    private Date timestamp;
    private String description;


}
