package com.wisecrm.crmapiservice.exceptions;

public class SuchEntityAlreadyExistsException extends RuntimeException{

    public SuchEntityAlreadyExistsException(String message) {
        super(message);
    }
}
