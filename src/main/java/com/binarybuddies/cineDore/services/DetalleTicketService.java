package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.DetalleTicket;
import com.binarybuddies.cineDore.repositories.DetalleTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DetalleTicketService {
    @Autowired
    private DetalleTicketRepository detalleTicketRepository;

    public List<DetalleTicket> getAll() {
        return detalleTicketRepository.findAll();
    }
}
