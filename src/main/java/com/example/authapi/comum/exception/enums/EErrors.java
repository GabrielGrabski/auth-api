package com.example.authapi.comum.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EErrors {

    USUARIO_NAO_ENCONTRADO("#001 - Usuário não encontrado."),
    USUARIO_INATIVO("#002 - Usuário Inativo."),
    ERRO_AUTENTICAR("#003 - Ocorreu um erro ao autenticar o usuário.");

    private String descricao;
}
