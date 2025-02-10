package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.DetalleTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleTicketService {
    @Autowired
    private DetalleTicketRepository detalleTicketRepository;

    public List<Detalle> getAll() {
        return detalleTicketRepository.findAll();
    }
}
