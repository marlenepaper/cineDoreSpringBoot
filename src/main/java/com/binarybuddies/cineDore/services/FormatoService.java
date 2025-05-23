package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Formato;
import com.binarybuddies.cineDore.repositories.FormatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class FormatoService {
    @Autowired
    private FormatoRepository formatoRepository;

    @Transactional
    public List<Formato> getAll() {
        return formatoRepository.findAll();
    }

    public Optional<Formato> getFormatoById(long id) {
        return Optional.of(this.formatoRepository.getById(id));
    }
}
