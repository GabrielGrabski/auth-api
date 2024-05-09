package com.grabski.authapi.modules.auth.service;

import com.grabski.authapi.infra.token.service.TokenService;
import com.grabski.authapi.modules.auth.dto.AuthResponse;
import com.grabski.authapi.modules.auth.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public AuthResponse login(LoginRequest request) {
        var user = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        var auth = authenticationManager.authenticate(user);
        return new AuthResponse(tokenService.generateToken(auth.getName()));
    }
}
