package com.grabski.authapi.modules.user.utils;

import com.grabski.authapi.modules.user.dto.UserRequest;
import com.grabski.authapi.modules.user.dto.UserUpdateRequest;
import com.grabski.authapi.modules.user.model.User;
import org.springframework.beans.BeanUtils;

public class UserGenerator {

    private UserGenerator() {

    }

    public static User from(UserRequest request, String password) {
        return User.builder()
                .email(request.email())
                .password(password)
                .name(request.name())
                .role(request.role())
                .build();
    }

    public static User mapUserFromRequest(User user, UserUpdateRequest request) {
        BeanUtils.copyProperties(request, user);
        return user;
    }
}
