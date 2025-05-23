package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.DetalleTicket;
import com.binarybuddies.cineDore.repositories.DetalleTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class DetalleTicketService {
    @Autowired
    private DetalleTicketRepository detalleTicketRepository;

    @Transactional
    public List<DetalleTicket> getAll() {
        return detalleTicketRepository.findAll();
    }

    public Optional<DetalleTicket> getDetalleTicketById(long id) {
        return Optional.of(this.detalleTicketRepository.getById(id));
    }
}
