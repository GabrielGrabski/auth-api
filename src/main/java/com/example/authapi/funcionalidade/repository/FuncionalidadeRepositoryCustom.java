package com.example.authapi.funcionalidade.repository;

import com.example.authapi.funcionalidade.model.Funcionalidade;

import java.util.List;

public interface FuncionalidadeRepositoryCustom {

    List<Funcionalidade> findByUsuarioId(Integer id);
}
