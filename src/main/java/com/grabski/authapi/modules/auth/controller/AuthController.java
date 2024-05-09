package com.grabski.authapi.modules.auth.controller;

import com.grabski.authapi.modules.auth.dto.AuthResponse;
import com.grabski.authapi.modules.auth.dto.LoginRequest;
import com.grabski.authapi.modules.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}
