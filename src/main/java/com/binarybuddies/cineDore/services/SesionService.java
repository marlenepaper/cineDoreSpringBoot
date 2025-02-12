package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Sesion;
import com.binarybuddies.cineDore.repositories.SesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class SesionService {
    @Autowired
    private SesionRepository sesionRepository;

    @Transactional
    public List<Sesion> getAll() {
        return sesionRepository.findAll();
    }
    public Optional<Sesion> getSesionById(long id) {
        return Optional.of(this.sesionRepository.getById(id));
    }
}
