package com.grabski.authapi.infra.token.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.grabski.authapi.modules.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${secure.jwt.secret}")
    private String key;

    public String generateToken(String username) {
        try {
            var algorithm = Algorithm.HMAC256(key);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(username)
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Cannot generate token");
        }
    }

    public String validateToken(String token) {
        try {
            var algorithm = Algorithm.HMAC256(key);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (Exception ex) {
            throw new RuntimeException("Cannot validate token");
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
