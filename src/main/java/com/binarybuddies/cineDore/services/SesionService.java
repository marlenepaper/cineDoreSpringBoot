package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesionService {
    @Autowired
    private SesionRepository sesionRepository;

    public List<Sesion> getAllSesiones() {
        return sesionRepository.findAll();
    }
}
