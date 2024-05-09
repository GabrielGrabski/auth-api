package com.grabski.authapi.common.errors.model.exception;

public class TokenGenerationException extends RuntimeException {

    public TokenGenerationException(String message) {
        super(message);
    }
}
