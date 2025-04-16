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

    @Autowired
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


    public List<TicketDisplayDTO> getTicketsByUserId(long usuarioId) {
        List<Compra> compras = compraRepository.findByUsuarioId(usuarioId);
        LocalDate fechaActual = LocalDate.now();
        System.out.println("compras cantidad:" + compras.size());
        return compras.stream()
                .filter(compra -> compra.getTicket() != null)
                .map(compra -> {
                    TicketEntrada ticket = compra.getTicket();

                    return new TicketDisplayDTO(
                            compra.getFuncion().getId(),
                            compra.getTotalPago(),
                            ticket.getCodigoQr(),
                            compra.getFuncion().getFechaHora().toString(),
                            compra.getFuncion().getPelicula().getNombre(),
                            compra.getFuncion().getPelicula().getImagenPoster(),
                            compra.getFuncion().getPelicula().getClasificacion().getNombre(),
                            compra.getFuncion().getPelicula().getLenguaje().getNombre(),
                            compra.getFuncion().getPelicula().getDuracion(),
                            compra.getFuncion().getSala().getNombre(),
                            ticket.getDetalles().size() // Cantidad total de entradas (detalle por entrada)
                    );
                })
                .filter(dto -> {
                    LocalDate fechaFuncion = LocalDate.parse(dto.getFechaFuncion().substring(0, 10));
                    return fechaFuncion.isEqual(fechaActual) || fechaFuncion.isBefore(fechaActual); //cambiar a isAfter
                })
                .sorted(Comparator.comparing(dto -> LocalDateTime.parse(dto.getFechaFuncion())))
                .collect(Collectors.toList());
    }
}
