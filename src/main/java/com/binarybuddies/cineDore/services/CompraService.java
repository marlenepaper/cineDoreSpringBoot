package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.repositories.CompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    public List<Compra> getAll() {
        return compraRepository.findAll();
    }
}
