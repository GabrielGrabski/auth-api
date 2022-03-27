package com.example.authapi.usuario.repository;

import com.example.authapi.usuario.enums.ESituacao;
import com.example.authapi.usuario.model.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@Sql("/scripts/usuario_test.sql")
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repository;

    @Test
    public void findByEmail_deveRetornarUsuario_quandoHouver() {
        var usuario = repository.findByEmail("GABRIEL@EMAIL.COM").orElseThrow();

        assertThat(usuario)
                .extracting(Usuario::getId,
                            Usuario::getNome,
                            Usuario::getEmail,
                            Usuario::getSituacao)
                .containsExactly(1,
                                "GABRIEL",
                                "GABRIEL@EMAIL.COM",
                                ESituacao.A);
    }
}
