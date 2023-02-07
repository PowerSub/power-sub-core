package com.powersub.core.exception;

import lombok.Getter;

@Getter
public class GenericException extends RuntimeException {

    private final String code;

    public GenericException(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
