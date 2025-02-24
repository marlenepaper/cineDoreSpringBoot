package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.*;
import com.binarybuddies.cineDore.models.*;
import com.binarybuddies.cineDore.repositories.CompraRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<TicketDisplayDTO> getTicketsByUserId(long usuarioId) {
        List<Compra> compras = compraRepository.findByUsuarioId(usuarioId);
        LocalDate fechaActual = LocalDate.now();

        return compras.stream().flatMap(compra ->
                compra.getTickets().stream().map(ticket -> new TicketDisplayDTO(
                        compra.getFuncion().getId(),
                        compra.getTotalPago(),
                        ticket.getCodigoQr(),
                        compra.getFuncion().getFechaHora().toString(),
                        compra.getFuncion().getPelicula().getNombre(),
                        compra.getFuncion().getPelicula().getImagenPoster(),
                        compra.getFuncion().getPelicula().getClasificacion().getNombre(),
                        compra.getFuncion().getPelicula().getLenguaje().getNombre(),
                        compra.getFuncion().getPelicula().getDuracion(),
                        compra.getTickets().size()
                ))
                )
                .filter(ticket -> LocalDate.parse(ticket.getFechaFuncion().substring(0, 10)).isAfter(fechaActual) ||
                        LocalDate.parse(ticket.getFechaFuncion().substring(0, 10)).isEqual(fechaActual)) // Filtra por fecha actual o futura
                .sorted(Comparator.comparing(ticket -> LocalDateTime.parse(ticket.getFechaFuncion())))// Ordenar por fecha ascendente
                .collect(Collectors.toList());
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

