package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAll() {
        return peliculaRepository.findAll();
    }
}
