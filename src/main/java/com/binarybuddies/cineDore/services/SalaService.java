package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    public List<Sala> getAll() {
        return salaRepository.findAll();
    }
}
