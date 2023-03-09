package com.powersub.core.exception;


public class ChannelDoesNotExistException extends GenericException {

    public ChannelDoesNotExistException(String msg) {
        super(msg, GenericExceptionCodes.CHANNEL_DOES_NOT_EXIST);
    }
}
