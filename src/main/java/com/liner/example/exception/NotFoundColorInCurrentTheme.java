package com.liner.example.exception;

import lombok.Getter;

@Getter
public class NotFoundColorInCurrentTheme extends RuntimeException{

    private ErrorCode errorCode;

    public NotFoundColorInCurrentTheme(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}