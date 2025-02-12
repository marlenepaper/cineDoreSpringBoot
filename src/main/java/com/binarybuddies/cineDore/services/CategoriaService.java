package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Categoria;
import com.binarybuddies.cineDore.repositories.CategoriaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional
    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> getCategoriaById(long id) {
        return Optional.of(this.categoriaRepository.getById(id));
    }
}
