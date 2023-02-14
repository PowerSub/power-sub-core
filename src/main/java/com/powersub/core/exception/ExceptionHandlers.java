package com.powersub.core.exception;

import com.powersub.core.entity.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDTO handleGenericException(GenericException exception, HttpServletRequest request) {
        log.warn(exception.getMessage(), exception);
        return new ExceptionDTO(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionDTO handleException(Exception exception, HttpServletRequest request) {
        log.error(exception.getMessage(), exception);
        return new ExceptionDTO(GenericExceptionCodes.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}