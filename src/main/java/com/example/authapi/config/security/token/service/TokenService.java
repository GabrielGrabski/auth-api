package com.example.authapi.config.security.token.service;

import com.example.authapi.usuario.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${app-config.security.token.secret}")
    private String secret;

    public String getToken(Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .setIssuer("Auth API")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86000))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            getTokenClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public Jws<Claims> getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
    }

    public Integer getUsuarioId(String token) {
        return Integer.getInteger(getTokenClaims(token)
                .getBody()
                .getSubject());
    }
}
