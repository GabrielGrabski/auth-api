package com.grabski.authapi.modules.user.dto;

import com.grabski.authapi.common.errors.messages.ErrorMessages;
import com.grabski.authapi.common.errors.model.exception.FieldValidationException;

import static com.nimbusds.oauth2.sdk.util.StringUtils.isBlank;
import static java.lang.String.format;

public record UserUpdateRequest(Integer id, String email, String name) {

    public UserUpdateRequest {
        if (id == null)
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "id"));
        if (isBlank(email))
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "email"));
        if (isBlank(name))
            throw new FieldValidationException(format(ErrorMessages.CANNOT_BE_EMPTY_OR_NULL.getMessage(), "name"));
    }
}
