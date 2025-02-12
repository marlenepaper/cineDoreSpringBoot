package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Cuenta;
import com.binarybuddies.cineDore.repositories.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Transactional
    public List<Cuenta> getAll() {
        return cuentaRepository.findAll();
    }

    public Optional<Cuenta> getCuentaById(long id) {
        return Optional.of(this.cuentaRepository.getById(id));
    }
}
