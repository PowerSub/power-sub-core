package com.powersub.core.exception;

public class InvalidChannelPermissionException extends GenericException {

    public InvalidChannelPermissionException(String msg) {
        super(msg, GenericExceptionCodes.INVALID_CHANNEL_PERMISSION);
    }
}
