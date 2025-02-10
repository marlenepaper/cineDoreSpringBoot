package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionService {
    @Autowired
    private FuncionRepository funcionRepository;

    public List<Funcion> getAll() {
        return funcionRepository.findAll();
    }
}
