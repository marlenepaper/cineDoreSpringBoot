package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Pelicula;
import com.binarybuddies.cineDore.repositories.PeliculaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<Pelicula> getAll() {
        return peliculaRepository.findAll();
    }


    public Optional<Pelicula> getPeliculaById(long id) {
        return Optional.of(this.peliculaRepository.getById(id));
    }


}
