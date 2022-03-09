package com.liner.example.exception;

import lombok.Getter;

@Getter
public class NotFound extends RuntimeException{

    private ErrorCode errorCode;

    public NotFound(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}