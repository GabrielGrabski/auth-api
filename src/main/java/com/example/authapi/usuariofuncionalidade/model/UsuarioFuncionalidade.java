package com.example.authapi.usuariofuncionalidade.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USUARIO_FUNCIONALIDADE")
public class UsuarioFuncionalidade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "USUARIO_ID")
    private Integer usuarioId;

    @Column(name = "FUNCIONALIDADE_ID")
    private Integer funcionalidadeId;
}
