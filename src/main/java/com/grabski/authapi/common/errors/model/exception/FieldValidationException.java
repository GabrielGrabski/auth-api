package com.grabski.authapi.common.errors.model.exception;

public class FieldValidationException extends RuntimeException {

    public FieldValidationException(String message) {
        super(message);
    }
}
