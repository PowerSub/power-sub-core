package com.powersub.core.exception;

public class InvalidCredentialsException extends GenericException {
    public InvalidCredentialsException(String msg) {
        super(msg, GenericExceptionCodes.INVALID_CREDENTIALS);
    }
}
