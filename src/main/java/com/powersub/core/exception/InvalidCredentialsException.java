package com.powersub.core.exception;

public class InvalidCredentialsException extends GenericException {
    public InvalidCredentialsException(String msg, String code) {
        super(msg, code);
    }
}
