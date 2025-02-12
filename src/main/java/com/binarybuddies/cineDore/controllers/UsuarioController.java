package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/ususarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.usuarioService.getUsuarioById(id));
    }
}
