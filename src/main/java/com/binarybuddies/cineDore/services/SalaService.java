package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Sala;
import com.binarybuddies.cineDore.repositories.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SalaService {
    @Autowired
    private SalaRepository salaRepository;

    @Transactional
    public List<Sala> getAll() {
        return salaRepository.findAll();
    }
}
