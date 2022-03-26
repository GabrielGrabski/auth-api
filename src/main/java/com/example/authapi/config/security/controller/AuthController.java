package com.example.authapi.config.security.controller;

import com.example.authapi.config.security.dto.LoginDto;
import com.example.authapi.config.security.service.AutenticacaoService;
import com.example.authapi.config.security.token.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AutenticacaoService service;

    @PostMapping
    public TokenResponse autenticar(@RequestBody LoginDto dto) {
        return service.autenticar(dto);
    }
}
