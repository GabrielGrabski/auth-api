package com.example.authapi.usuario.controller;

import com.example.authapi.usuario.dto.UsuarioResponse;
import com.example.authapi.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("{id}")
    public UsuarioResponse getById(@PathVariable Integer id) {
        return service.findById(id);
    }
}
