package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
}
