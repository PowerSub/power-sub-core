package com.powersub.core.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException{

    private final String code;

    public GlobalException(String msg, String code) {
        super(msg);
        this.code = code;
    }
}
