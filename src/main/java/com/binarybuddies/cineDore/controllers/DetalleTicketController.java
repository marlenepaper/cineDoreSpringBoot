package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.DetalleTicket;
import com.binarybuddies.cineDore.services.DetalleTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/detalles_ticket")
@CrossOrigin(origins = "*")
public class DetalleTicketController {

    @Autowired
    private DetalleTicketService detalleTicketService;

    @GetMapping
    public ResponseEntity<List<DetalleTicket>> getAllDetalleTickets() {
        List<DetalleTicket> detalleTickets = detalleTicketService.getAll();
        return ResponseEntity.ok(detalleTickets);
    }
}
