package com.grabski.authapi.modules.user.dto;

import com.grabski.authapi.modules.user.enums.role.Role;
import jakarta.validation.constraints.NotNull;

public record UserRequest(@NotNull String name, @NotNull String email, @NotNull String password, @NotNull Role role) {
}
