package com.wisecrm.customerservice.exceptions;

public class SuchEntityAlreadyExists extends RuntimeException{
    public SuchEntityAlreadyExists() {

    }

    public SuchEntityAlreadyExists(String message) {
        super(message);
    }
}
