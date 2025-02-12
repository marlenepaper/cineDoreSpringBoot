package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.repositories.TicketEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
@Service
public class TicketEntradaService {
    @Autowired
    private TicketEntradaRepository ticketEntradaRepository;

    @Transactional
    public List<TicketEntrada> getAll() {
        return ticketEntradaRepository.findAll();
    }
    public Optional<TicketEntrada> getTicketEntradaById(long id) {
        return Optional.of(this.ticketEntradaRepository.getById(id));
    }
}
