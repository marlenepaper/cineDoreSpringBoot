package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.ClasificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClasificacionService {
    @Autowired
    private ClasificacionRepository clasificacionRepository;

    public List<Clasificacion> getAll() {
        return clasificacionRepository.findAll();
    }
}
