package com.grabski.authapi.modules.user.dto;

import com.grabski.authapi.modules.user.enums.role.Role;

public record UpdateRoleUserRequest(String email, Role role) {
}
