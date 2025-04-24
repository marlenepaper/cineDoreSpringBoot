package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.TicketDisplayDTO;
import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.services.TicketEntradaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Tickets", description = "Operaciones con tickets de entrada")
@RestController
@RequestMapping("/tickets")
@CrossOrigin(origins = "*")
public class TicketEntradaController {

    @Autowired
    private TicketEntradaService ticketEntradaService;

    @Operation(summary = "Obtener lista de tickets de entrada")
    @ApiResponse(responseCode = "200", description = "Lista de tickets encontrada")
    @GetMapping
    public ResponseEntity<List<TicketEntrada>> getAllTicketsEntrada() {
        List<TicketEntrada> ticketsEntrada = ticketEntradaService.getAll();
        return ResponseEntity.ok(ticketsEntrada);
    }

    @Operation(summary = "Obtener ticket de entrada por ID")
    @Parameter(name = "id", required = true)
    @ApiResponse(responseCode = "200", description = "Ticket de entrada encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TicketEntrada>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.ticketEntradaService.getTicketEntradaById(id));
    }

    @Operation(summary = "Obtener lista de tickets por ID de usuario")
    @Parameter(name = "usuarioId", required = true)
    @ApiResponse(responseCode = "200", description = "Lista de tickets encontrada")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketDisplayDTO>> getTicketsByUserId(@PathVariable long usuarioId) {
        List<TicketDisplayDTO> tickets = ticketEntradaService.getTicketsByUserId(usuarioId);
        return ResponseEntity.ok(tickets);
    }
}
