package com.grabski.authapi.modules.user.controller;

import com.grabski.authapi.common.dto.GenericRestResponse;
import com.grabski.authapi.modules.user.dto.UpdateRoleUserRequest;
import com.grabski.authapi.modules.user.dto.UserRequest;
import com.grabski.authapi.modules.user.dto.UserResponse;
import com.grabski.authapi.modules.user.dto.UserUpdateRequest;
import com.grabski.authapi.modules.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public Page<UserResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    public GenericRestResponse<String> createUser(@RequestBody @Valid UserRequest request) {
        return service.register(request);
    }

    @PatchMapping("update-role")
    public GenericRestResponse<String> updateRole(@RequestBody UpdateRoleUserRequest request) {
        return service.updateRole(request);
    }

    @PutMapping
    public GenericRestResponse<String> updateUser(@RequestBody UserUpdateRequest request) {
        return service.update(request);
    }
}
