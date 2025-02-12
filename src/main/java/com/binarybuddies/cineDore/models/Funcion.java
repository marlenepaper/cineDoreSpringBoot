package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "funciones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Funcion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_pelicula", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Pelicula pelicula;

    @Column(name = "fecha_hora", nullable = false)
    @NotNull(message = "Fecha y hora no pueden estar vacias")
    @Future(message = "La fecha de la función debe ser futura")
    private LocalDateTime fechaHora;

    @ManyToOne
    @JoinColumn(name = "id_sala", referencedColumnName = "id", nullable = false)
    @JsonBackReference
    private Sala sala;

    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Compra> compras= new ArrayList<>();
}
