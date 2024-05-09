package com.grabski.authapi.modules.user.service;

import com.grabski.authapi.common.dto.GenericRestResponse;
import com.grabski.authapi.modules.user.dto.UserRequest;
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

    public GenericRestResponse<String> createUser(UserRequest request) {
        repository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .role(request.role())
                .build());

        return new GenericRestResponse<>(201, format("User %s created successfully!", request.email()));
    }
}
