package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> getUsuarioById(long id) {
        return Optional.of(this.usuarioRepository.getById(id));
    }
}
