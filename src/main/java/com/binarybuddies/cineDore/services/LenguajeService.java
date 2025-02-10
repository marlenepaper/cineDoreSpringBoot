package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.LenguajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LenguajeService {
    @Autowired
    private LenguajeRepository lenguajeRepository;

    public List<Lenguaje> getAll() {
        return lenguajeRepository.findAll();
    }
}
