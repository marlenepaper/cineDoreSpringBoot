package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.TicketDisplayDTO;
import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.repositories.CompraRepository;
import com.binarybuddies.cineDore.repositories.TicketEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
@Service
public class TicketEntradaService {
    @Autowired
    private TicketEntradaRepository ticketEntradaRepository;

    private CompraRepository compraRepository;

    @Transactional
    public List<TicketEntrada> getAll() {
        return ticketEntradaRepository.findAll();
    }
    public Optional<TicketEntrada> getTicketEntradaById(long id) {
        return Optional.of(this.ticketEntradaRepository.getById(id));
    }

    public TicketEntrada guardarTicket(TicketEntrada ticket) {
        return ticketEntradaRepository.save(ticket);
    }

    public boolean esActivo(TicketEntrada ticket) {
        return ticket.getEstado() == 1;
    }

    public boolean esInactivo(TicketEntrada ticket) {
        return ticket.getEstado() == 2;
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
}
