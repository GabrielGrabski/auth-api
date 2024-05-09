package com.grabski.authapi.modules.user.service;

import com.grabski.authapi.common.dto.GenericRestResponse;
import com.grabski.authapi.common.messages.SuccessMessages;
import com.grabski.authapi.modules.user.dto.UpdateRoleUserRequest;
import com.grabski.authapi.modules.user.dto.UserRequest;
import com.grabski.authapi.modules.user.dto.UserResponse;
import com.grabski.authapi.modules.user.dto.UserUpdateRequest;
import com.grabski.authapi.modules.user.repository.UserRepository;
import com.grabski.authapi.modules.user.utils.UserGenerator;
import com.grabski.authapi.modules.user.utils.UserValidation;
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

    public Page<UserResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(user -> new UserResponse(user.getEmail(), user.getName(), user.getRole()));
    }

    public GenericRestResponse<String> register(UserRequest request) {
        UserValidation.validateUserRegistration(request);
        repository.save(UserGenerator.from(request, passwordEncoder.encode(request.password())));
        return new GenericRestResponse<>(201,
                format(SuccessMessages.USER_REGISTERED.getMessage(), request.email()));
    }

    public GenericRestResponse<String> update(UserUpdateRequest request) {
        var user = repository.findById(request.id()).orElseThrow();
        repository.save(UserGenerator.mapUserFromRequest(user, request));
        return new GenericRestResponse<>(200, SuccessMessages.USER_UPDATED.getMessage());
    }

    public GenericRestResponse<String> updateRole(UpdateRoleUserRequest request) {
        var user = repository.findByEmail(request.email()).orElseThrow();

        user.setRole(request.role());
        repository.save(user);

        return new GenericRestResponse<>(200,
                format(SuccessMessages.USER_ROLE_UPDATED.getMessage(), request.email(), request.role()));
    }
}
