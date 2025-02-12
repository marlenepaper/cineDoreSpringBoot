package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.repositories.FuncionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Service
public class FuncionService {
    @Autowired
    private FuncionRepository funcionRepository;

    @Transactional
    public List<Funcion> getAll() {
        return funcionRepository.findAll();
    }
}
