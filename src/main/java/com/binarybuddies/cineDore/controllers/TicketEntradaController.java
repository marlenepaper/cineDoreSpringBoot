package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.services.TicketEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tickets_entrada")
@CrossOrigin(origins = "*")
public class TicketEntradaController {

    @Autowired
    private TicketEntradaService ticketEntradaService;

    @GetMapping
    public ResponseEntity<List<TicketEntrada>> getAllTicketsEntrada() {
        List<TicketEntrada> ticketsEntrada = ticketEntradaService.getAll();
        return ResponseEntity.ok(ticketsEntrada);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TicketEntrada>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.ticketEntradaService.getTicketEntradaById(id));
    }
}
