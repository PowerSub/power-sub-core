package com.powersub.core.exception;

public class AccountDoesNotExistException extends GenericException {
    public AccountDoesNotExistException(String msg) {
        super(msg, GenericExceptionCodes.ACCOUNT_DOES_NOT_EXIST);
    }
}
