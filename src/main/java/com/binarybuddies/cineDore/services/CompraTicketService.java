package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.*;
import com.binarybuddies.cineDore.models.*;
import com.binarybuddies.cineDore.repositories.CompraRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompraTicketService {

    private final CompraRepository compraRepository;
    private final TicketEntradaService ticketEntradaService;
    private final UsuarioService usuarioService;
    private final FuncionService funcionService;

    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public Optional<Compra> getCompraById(long id) {
        return compraRepository.findById(id);
    }

    public List<Compra> getTicketsByUserId(long usuarioId) {
        return compraRepository.findByUsuarioId(usuarioId);
    }


    @Transactional
    public Compra crearCompra(CompraDTO compraDTO) {

        Usuario usuario = usuarioService.getUsuarioById(compraDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        Funcion funcion = funcionService.getFuncionById(compraDTO.getFuncionId())
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));


        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setFuncion(funcion);
        compra.setTotalPago(compraDTO.getTotalPago());


        Compra compraGuardada = compraRepository.save(compra);

        // Procesar cada ticket de la compra
        for (TicketEntradaDTO ticketDTO : compraDTO.getTickets()) {
            TicketEntrada ticket = new TicketEntrada();
            ticket.setCompra(compraGuardada);
            ticket.setCodigoQr(ticketDTO.getCodigoQr());

            // Establecer estado activo (asegúrate de que el ID 1 exista en la tabla de estados_ticket)
            EstadoTicket estadoActivo = new EstadoTicket();
            estadoActivo.setId(1L);
            ticket.setEstado(estadoActivo);

            ticketEntradaService.guardarTicket(ticket);
        }

        return compraGuardada;
    }
}

