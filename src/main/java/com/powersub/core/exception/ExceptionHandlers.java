package com.powersub.core.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlers {


    @ExceptionHandler(GlobalException.class )
    public void handleGlobalException() {
        //
    }

    @ExceptionHandler(Exception.class )
    public void handleException() {
        //
    }
}
