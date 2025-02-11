package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Sesion;
import com.binarybuddies.cineDore.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SesionService {
    @Autowired
    private SesionRepository sesionRepository;

    public List<Sesion> getAll() {
        return sesionRepository.findAll();
    }
}
