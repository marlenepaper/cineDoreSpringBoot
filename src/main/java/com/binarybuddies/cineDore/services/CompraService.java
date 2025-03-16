package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.CompraDTO;
import com.binarybuddies.cineDore.dto.TicketEntradaDTO;
import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.models.TicketEntrada;
import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.repositories.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    private final UsuarioService usuarioService;
    private final FuncionService funcionService;
    private final TicketEntradaService ticketEntradaService;

    @Transactional
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public Optional<Compra> getCompraById(long id) {
        return Optional.of(this.compraRepository.getById(id));
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

            // Establecer estado activo
            ticket.setEstado(1);

            ticketEntradaService.guardarTicket(ticket);
        }

        return compraGuardada;
    }
}
