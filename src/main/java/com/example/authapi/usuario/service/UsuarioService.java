package com.example.authapi.usuario.service;

import com.example.authapi.comum.exception.enums.EErrors;
import com.example.authapi.comum.exception.model.ValidacaoException;
import com.example.authapi.usuario.dto.UsuarioResponse;
import com.example.authapi.usuario.enums.ESituacao;
import com.example.authapi.usuario.model.Usuario;
import com.example.authapi.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.authapi.comum.exception.enums.EErrors.USUARIO_INATIVO;
import static com.example.authapi.comum.exception.enums.EErrors.USUARIO_NAO_ENCONTRADO;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioResponse findById(Integer id) {
        var usuario = repository
                .findById(id)
                .orElseThrow(() -> new ValidacaoException(USUARIO_NAO_ENCONTRADO.getDescricao()));
        return UsuarioResponse.of(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = repository
                .findByEmail(username)
                .orElseThrow(() -> new ValidacaoException(USUARIO_NAO_ENCONTRADO.getDescricao()));
        validarSituacao(usuario);
        return usuario;
    }

    private void validarSituacao(Usuario usuario) {
        if (!usuario.getSituacao().equals(ESituacao.A)) {
            throw new ValidacaoException(USUARIO_INATIVO.getDescricao());
        }
    }
}
