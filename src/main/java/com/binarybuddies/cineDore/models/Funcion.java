package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "funcion")
public class Funcion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pelicula", referencedColumnName = "id", nullable = false)
    private Pelicula idPelicula;

    @Column(name = "fecha_hora", nullable = false)
    @NotNull(message = "Fecha y hora cannot be null")
    @Future(message = "La fecha de la función debe ser futura")
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id", nullable = false)
    private Sala idSala;

    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras;
}
