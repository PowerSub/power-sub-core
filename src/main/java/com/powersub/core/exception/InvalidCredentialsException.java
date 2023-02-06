package com.powersub.core.exception;

import lombok.NoArgsConstructor;

public class InvalidCredentialsException extends GlobalException {
    public InvalidCredentialsException(String msg, String code) {
        super(msg, code);
    }
}
