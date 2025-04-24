package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.DetalleTicket;
import com.binarybuddies.cineDore.services.DetalleTicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Detalle del ticket", description = "Operaciones con detalles del ticket")
@RestController
@RequestMapping("/detalles_ticket")
@CrossOrigin(origins = "*")
public class DetalleTicketController {

    @Autowired
    private DetalleTicketService detalleTicketService;

    @Operation(summary = "Obtener lista de detalles del ticket")
    @ApiResponse(responseCode = "200", description = "Lista de detalles encontrada")
    @GetMapping
    public ResponseEntity<List<DetalleTicket>> getAllDetalleTickets() {
        List<DetalleTicket> detalleTickets = detalleTicketService.getAll();
        return ResponseEntity.ok(detalleTickets);
    }

    @Operation(summary = "Obtener detalle del ticket por ID")
    @Parameter(name = "id", required = true)
    @ApiResponse(responseCode = "200", description = "Detalle del ticket encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DetalleTicket>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.detalleTicketService.getDetalleTicketById(id));
    }
}
