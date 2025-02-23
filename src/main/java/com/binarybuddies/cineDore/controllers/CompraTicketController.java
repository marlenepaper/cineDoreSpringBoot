package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.*;
import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.services.AuthService;
import com.binarybuddies.cineDore.services.CompraService;
import com.binarybuddies.cineDore.services.CompraTicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/compraTicket")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CompraTicketController {

    private final CompraTicketService compraTicketService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraTicketService.getAll();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compra>> getCompraById(@PathVariable long id) {
        Optional<Compra> compra = compraTicketService.getCompraById(id);
        return ResponseEntity.ok(compra);
    }

    @PostMapping("/crear")
    public ResponseEntity<Compra> crearCompra(@RequestBody CompraDTO compraDTO) {
        Compra nuevaCompra = compraTicketService.crearCompra(compraDTO);
        return ResponseEntity.ok(nuevaCompra);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Compra>> getTicketsByUserId(@PathVariable long usuarioId) {
        List<Compra> compras = compraTicketService.getTicketsByUserId(usuarioId);
        return ResponseEntity.ok(compras);
    }

}