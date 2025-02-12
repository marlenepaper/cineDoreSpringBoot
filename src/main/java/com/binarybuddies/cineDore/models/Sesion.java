package com.binarybuddies.cineDore.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sesiones")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sesion
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta", referencedColumnName = "id", nullable = false)
    private Cuenta cuenta;
}
