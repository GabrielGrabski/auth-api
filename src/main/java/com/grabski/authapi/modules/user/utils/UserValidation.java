package com.grabski.authapi.modules.user.utils;

import com.grabski.authapi.common.errors.messages.ErrorMessages;
import com.grabski.authapi.common.errors.model.exception.FieldValidationException;
import com.grabski.authapi.common.errors.model.exception.UserRegistrationException;
import com.grabski.authapi.modules.user.dto.UserRequest;
import com.grabski.authapi.modules.user.enums.role.Role;

import static com.nimbusds.oauth2.sdk.util.StringUtils.isBlank;

public class UserValidation {

    private UserValidation() {

    }

    public static void validateUserRegistration(UserRequest request) {
        validateEmptyPassword(request);
        validateAdminRole(request);
    }

    public static void validateEmptyPassword(UserRequest request) {
        if (isBlank(request.password())) throw new FieldValidationException(ErrorMessages.EMPTY_PASSWORD.getMessage());
    }

    public static void validateAdminRole(UserRequest request) {
        if (Role.ADMIN.equals(request.role()))
            throw new UserRegistrationException(ErrorMessages.CANNOT_REGISTER_ADMIN_USER.getMessage());
    }

    public static void validateBlankPassword(String password) {
        if (password.isBlank()) throw new FieldValidationException(ErrorMessages.BLANK_PASSWORD.getMessage());
    }
}
