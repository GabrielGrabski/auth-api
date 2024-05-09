package com.grabski.authapi.common.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessMessages {

    USER_REGISTERED("User %s registered successfully."),
    USER_ROLE_UPDATED("User %s role updated to %s successfully."),
    USER_UPDATED("User updated successfully.");

    private final String message;
}
