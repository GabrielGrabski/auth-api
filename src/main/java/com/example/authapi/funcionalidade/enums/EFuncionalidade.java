package com.example.authapi.funcionalidade.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EFuncionalidade {

    ADMIN_001("CRIAR USUÁRIOS"),
    USER_001("VIZUALIZAR DADOS PESSOAIS");

    private String descricao;
}
