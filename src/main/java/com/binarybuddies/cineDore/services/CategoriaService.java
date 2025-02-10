package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Categoria;
import com.binarybuddies.cineDore.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getAll() {
        return categoriaRepository.findAll();
    }
}
