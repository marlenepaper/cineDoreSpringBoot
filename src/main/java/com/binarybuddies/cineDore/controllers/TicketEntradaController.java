package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.services.TicketEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/tickets_entrada")
@CrossOrigin(origins = "*")
public class TicketEntradaController {

    @Autowired
    private TicketEntradaService ticketEntradaService;

    @GetMapping
    public List<TicketEntrada> getAllTicketEntradas() {
        return ticketEntradaService.getAll();
    }
}
