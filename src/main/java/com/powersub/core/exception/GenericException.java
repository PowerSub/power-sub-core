package com.powersub.core.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private final GenericExceptionCodes code;

    public GenericException(String msg, GenericExceptionCodes code) {
        super(msg);
        this.code = code;
    }
}
