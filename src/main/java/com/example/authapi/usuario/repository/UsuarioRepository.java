package com.example.authapi.usuario.repository;

import com.example.authapi.usuario.model.Usuario;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}
