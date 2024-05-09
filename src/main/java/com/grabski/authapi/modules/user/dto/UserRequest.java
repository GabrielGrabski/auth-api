package com.grabski.authapi.modules.user.dto;

import com.grabski.authapi.common.errors.messages.ErrorMessages;
import com.grabski.authapi.common.errors.model.exception.FieldValidationException;
import com.grabski.authapi.modules.user.enums.role.Role;
import jakarta.validation.constraints.Email;

import static com.nimbusds.oauth2.sdk.util.StringUtils.isBlank;
import static java.lang.String.format;

public record UserRequest(String name, @Email String email, String password, Role role) {

    public UserRequest {
        if (isBlank(name))
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "name"));
        if (isBlank(email))
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "email"));
        if (isBlank(password))
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "password"));
        if (role == null)
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "role"));
    }
}
