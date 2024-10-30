package com.wisecrm.crmapiservice.exceptions;

public class WrongApiKeyException extends RuntimeException{
    public WrongApiKeyException(String message) {
        super(message);
    }
}
