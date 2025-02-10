package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.FormatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormatoService {
    @Autowired
    private FormatoRepository formatoRepository;

    public List<Formato> getAll() {
        return formatoRepository.findAll();
    }
}
