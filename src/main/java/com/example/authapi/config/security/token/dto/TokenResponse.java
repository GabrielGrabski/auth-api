package com.example.authapi.config.security.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String token;
    private String tipo;

    public static TokenResponse of(String token, String tipo) {
        return TokenResponse.builder()
                .token(token)
                .tipo(tipo)
                .build();
    }
}
