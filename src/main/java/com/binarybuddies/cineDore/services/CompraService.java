package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    @Transactional
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public Optional<Compra> getCompraById(long id) {
        return Optional.of(this.compraRepository.getById(id));
    }
}
