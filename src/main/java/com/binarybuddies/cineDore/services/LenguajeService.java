package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Lenguaje;
import com.binarybuddies.cineDore.repositories.LenguajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class LenguajeService {
    @Autowired
    private LenguajeRepository lenguajeRepository;

    @Transactional
    public List<Lenguaje> getAll() {
        return lenguajeRepository.findAll();
    }

    public Optional<Lenguaje> getLenguajeById(long id) {
        return Optional.of(this.lenguajeRepository.getById(id));
    }
}
