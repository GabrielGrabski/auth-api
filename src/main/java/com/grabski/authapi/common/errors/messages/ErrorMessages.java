package com.grabski.authapi.common.errors.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    CANNOT_REGISTER_ADMIN_USER("User cannot be registered with ADMIN role."),
    CANNOT_GENERATE_TOKEN("Cannot generate token. Contact system admin"),
    CANNOT_VALIDATE_TOKEN("Cannot validate token. Contact system admin"),
    EMPTY_PASSWORD("Password cannot be empty or null."),
    CANNOT_BE_EMPTY_OR_NULL("%s cannot be null or empty"),
    BLANK_PASSWORD("Password cannot be blank.");

    private final String message;
}
