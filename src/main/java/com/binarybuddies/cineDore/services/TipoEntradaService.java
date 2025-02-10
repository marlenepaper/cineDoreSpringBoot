package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.TipoEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoEntradaService {
    @Autowired
    private TipoEntradaRepository tipoEntradaRepository;

    public List<TipoEntrada> getAll() {
        return tipoEntradaRepository.findAll();
    }
}
