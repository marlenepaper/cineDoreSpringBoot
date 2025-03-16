package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.TicketDisplayDTO;
import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.services.TicketEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tickets")
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

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketDisplayDTO>> getTicketsByUserId(@PathVariable long usuarioId) {
        List<TicketDisplayDTO> tickets = ticketEntradaService.getTicketsByUserId(usuarioId);
        return ResponseEntity.ok(tickets);
    }
}
