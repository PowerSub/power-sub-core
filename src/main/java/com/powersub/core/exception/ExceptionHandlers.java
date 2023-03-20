package com.powersub.core.exception;

import com.powersub.core.entity.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO handleGenericException(GenericException exception) {
        log.warn(exception.getMessage());
        return new ExceptionDTO(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO validationException(ValidationException exception) {
        log.warn(exception.getMessage());
        return new ExceptionDTO(GenericExceptionCodes.BAD_VALIDATION, exception.getMessage());
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDTO notNullValidationException(BindException exception) {
        log.warn(exception.getMessage());
        return new ExceptionDTO(GenericExceptionCodes.BAD_VALIDATION, exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDTO handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return new ExceptionDTO(GenericExceptionCodes.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
