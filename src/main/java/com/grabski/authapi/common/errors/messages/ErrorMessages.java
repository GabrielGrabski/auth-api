package com.grabski.authapi.common.errors.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {

    CANNOT_REGISTER_ADMIN_USER("User cannot be registered with ADMIN role."),
    CANNOT_GENERATE_TOKEN("Cannot generate token. Contact system admin"),
    CANNOT_VALIDATE_TOKEN("Cannot validate token. Contact system admin");

    private final String message;
}
