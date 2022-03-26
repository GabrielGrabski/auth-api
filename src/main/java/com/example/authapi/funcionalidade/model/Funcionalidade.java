package com.example.authapi.funcionalidade.model;

import com.example.authapi.usuario.model.Usuario;
import com.example.authapi.funcionalidade.enums.EFuncionalidade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNCIONALIDADE")
public class Funcionalidade {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name ="DESCRICAO")
    private String descricao;

    @Column(name ="CODIGO")
    @Enumerated(EnumType.STRING)
    private EFuncionalidade codigo;

    @ManyToMany(mappedBy = "funcionalidades")
    private List<Usuario> usuarios;
}
