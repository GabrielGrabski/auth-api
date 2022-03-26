package com.example.authapi.funcionalidade.repository;

import com.example.authapi.comum.repository.CustomRepository;
import com.example.authapi.funcionalidade.model.Funcionalidade;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.authapi.funcionalidade.model.QFuncionalidade.funcionalidade;
import static com.example.authapi.usuariofuncionalidade.model.QUsuarioFuncionalidade.usuarioFuncionalidade;

@Repository
public class FuncionalidadeRepositoryImpl extends CustomRepository
        implements FuncionalidadeRepositoryCustom {

    @Override
    public List<Funcionalidade> findByUsuarioId(Integer id) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(funcionalidade)
                .innerJoin(usuarioFuncionalidade)
                .on(usuarioFuncionalidade.usuarioId.eq(id))
                .orderBy(funcionalidade.codigo.asc())
                .fetch();
    }
}
