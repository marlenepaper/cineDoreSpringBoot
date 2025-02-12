package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Clasificacion;
import com.binarybuddies.cineDore.repositories.ClasificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ClasificacionService {
    @Autowired
    private ClasificacionRepository clasificacionRepository;

    @Transactional
    public List<Clasificacion> getAll() {
        return clasificacionRepository.findAll();
    }
}
