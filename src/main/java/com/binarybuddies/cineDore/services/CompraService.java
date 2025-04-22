package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.CompraDTO;
import com.binarybuddies.cineDore.dto.DetalleTicketDTO;
import com.binarybuddies.cineDore.models.*;
import com.binarybuddies.cineDore.repositories.CompraRepository;
import com.binarybuddies.cineDore.repositories.DetalleTicketRepository;
import com.binarybuddies.cineDore.repositories.TipoEntradaRepository;
import com.binarybuddies.cineDore.utils.QRGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompraService {
    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final FuncionService funcionService;

    @Autowired
    private final TicketEntradaService ticketEntradaService;

    @Autowired
    private final TipoEntradaRepository tipoEntradaRepository;

    @Autowired
    private final DetalleTicketRepository detalleTicketRepository;

    @Transactional
    public List<Compra> getAll() {
        return compraRepository.findAll();
    }

    public Optional<Compra> getCompraById(long id) {
        return Optional.of(this.compraRepository.getById(id));
    }

    /**
     * Crea una compra con una compraDTO
     *
     * @param compraDTO La compra que llegará del front con datos de funcion y usuario
     * @return Compra con su ticket correspondiente
     * @throws RuntimeException si no encuentra el usuario o la función
     * @throws RuntimeException si no encuentra el tipo de entrada
     */

    @Transactional
    public Compra crearCompra(CompraDTO compraDTO) throws Exception {

        Usuario usuario = usuarioService.getUsuarioById(compraDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        Funcion funcion = funcionService.getFuncionById(compraDTO.getFuncionId())
                .orElseThrow(() -> new RuntimeException("Función no encontrada"));

        /*
         * Crea una compra si encuentra el usuario y la función.
         * La guarda en la base de datos
         */

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setFuncion(funcion);
        compra.setTotalPago(compraDTO.getTotalPago());

        Compra compraGuardada = compraRepository.save(compra);

        // Crea 1 solo ticket asociado a esa compra
        TicketEntrada ticket = new TicketEntrada();
        ticket.setCompra(compraGuardada);
        TicketEntrada ticketGuardado = ticketEntradaService.guardarTicket(ticket);

        //Generar código QR
        String qrContent = "Compra ID: " + compraGuardada.getId() + ", Ticket ID: " + ticketGuardado.getId();
        String qrBase64 = QRGenerator.generateQrBase64(qrContent);

        //Asocia el codigo qr con el ticket
        ticketGuardado.setCodigoQr(qrBase64);
        ticketEntradaService.guardarTicket(ticketGuardado);
        compraGuardada.setTicket(ticketGuardado);

        //Lista de detalles asociada al ticket. Cada detalle es 1 entrada
        List<DetalleTicket> detalles = new ArrayList<>();


        for (DetalleTicketDTO detalleDTO : compraDTO.getTickets()) {
            TipoEntrada tipoEntrada = tipoEntradaRepository.findById(detalleDTO.getTipoEntradaId())
                    .orElseThrow(() -> new RuntimeException("Tipo de entrada no encontrado"));

            //Genera un detalle ticket por cada uno que haya llegado de detalleDTO
            for (int i = 0; i < detalleDTO.getCantidad(); i++) {
                // Relacionarlo con su tipo
                DetalleTicket detalle = new DetalleTicket();
                detalle.setTicketEntrada(ticketGuardado);
                detalle.setTipoEntrada(tipoEntrada);
                detalle.setEstado(1);
                detalles.add(detalle);
            }

        }

        //Guarda todos los detalles ticket (cada entrada)
        detalleTicketRepository.saveAll(detalles);
        return compraGuardada;
    }
}
