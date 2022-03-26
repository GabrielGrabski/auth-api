package com.example.authapi.usuario.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ESituacao {

    A("Ativo"),
    I("Inativo");

    private String descricao;
}
