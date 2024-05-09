package com.grabski.authapi.common.errors.model.exception;

public class UserRegistrationException extends RuntimeException {

    public UserRegistrationException(String message) {
        super(message);
    }
}
