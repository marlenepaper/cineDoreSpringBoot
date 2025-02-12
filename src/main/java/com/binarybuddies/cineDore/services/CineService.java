package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Cine;
import com.binarybuddies.cineDore.repositories.CineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CineService {

    @Autowired
    private CineRepository cineRepository;

    @Transactional
    public List<Cine> getAll() {
        return cineRepository.findAll();
    }

    public Optional<Cine> getCineById(long id) {
        return Optional.of(this.cineRepository.getById(id));
    }
}
