package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.TicketEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketEntradaService {
    @Autowired
    private TicketEntradaRepository ticketEntradaRepository;

    public List<TicketEntrada> getAll() {
        return ticketEntradaRepository.findAll();
    }
}
