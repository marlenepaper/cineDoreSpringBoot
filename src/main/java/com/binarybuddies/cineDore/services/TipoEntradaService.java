package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.TipoEntrada;
import com.binarybuddies.cineDore.repositories.TipoEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoEntradaService {
    @Autowired
    private TipoEntradaRepository tipoEntradaRepository;

    @Transactional
    public List<TipoEntrada> getAll() {
        return tipoEntradaRepository.findAll();
    }
    public Optional<TipoEntrada> getTipoEntradaById(long id) {
        return this.tipoEntradaRepository.getReferenceById(id);
    }
}
