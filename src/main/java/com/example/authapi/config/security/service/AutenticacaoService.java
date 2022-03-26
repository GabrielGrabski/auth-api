package com.example.authapi.config.security.service;

import com.example.authapi.comum.exception.model.ValidacaoException;
import com.example.authapi.config.security.dto.LoginDto;
import com.example.authapi.config.security.token.dto.TokenResponse;
import com.example.authapi.config.security.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import static com.example.authapi.comum.exception.enums.EErrors.ERRO_AUTENTICAR;

@Service
public class AutenticacaoService {

    private static final String BEARER = "Bearer";

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager authManager;

    public TokenResponse autenticar(LoginDto dto) {
        try {
            var usuario = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
            var auth = authManager.authenticate(usuario);
            return TokenResponse.of(tokenService.getToken(auth), BEARER);
        } catch (AuthenticationException ex) {
            throw new ValidacaoException(ERRO_AUTENTICAR.getDescricao());
        }
    }
}
