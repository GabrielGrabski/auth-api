package com.grabski.authapi.modules.user.service;

import com.grabski.authapi.common.dto.GenericRestResponse;
import com.grabski.authapi.common.errors.messages.ErrorMessages;
import com.grabski.authapi.common.errors.model.exception.UserRegistrationException;
import com.grabski.authapi.common.messages.SuccessMessages;
import com.grabski.authapi.modules.user.dto.UpdateRoleUserRequest;
import com.grabski.authapi.modules.user.dto.UserRequest;
import com.grabski.authapi.modules.user.enums.role.Role;
import com.grabski.authapi.modules.user.model.User;
import com.grabski.authapi.modules.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public GenericRestResponse<String> register(UserRequest request) {
        validateAdminRole(request);

        repository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .role(request.role())
                .build());

        return new GenericRestResponse<>(201,
                format(SuccessMessages.USER_REGISTERED.getMessage(), request.email())
        );
    }

    public GenericRestResponse<String> updateRole(UpdateRoleUserRequest request) {
        var user = repository.findByEmail(request.email()).orElseThrow();

        user.setRole(request.role());
        repository.save(user);

        return new GenericRestResponse<>(200,
                format(SuccessMessages.USER_UPDATED.getMessage(), request.email(), request.role()));
    }

    private void validateAdminRole(UserRequest request) {
        if (Role.ADMIN.equals(request.role()))
            throw new UserRegistrationException(ErrorMessages.CANNOT_REGISTER_ADMIN_USER.getMessage());
    }
}
