package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Cine;
import com.binarybuddies.cineDore.repositories.CineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CineService {

    @Autowired
    private CineRepository cineRepository;

    public List<Cine> getAll() {
        return cineRepository.findAll();
    }
}
