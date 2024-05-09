package com.grabski.authapi.modules.user.dto;

import com.grabski.authapi.modules.user.enums.role.Role;

public record UserResponse(String email, String name, Role role) {
}
