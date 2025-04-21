package com.binarybuddies.cineDore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "carrusel")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Carrusel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String subtitulo;

    @Column(nullable = false)
    private String imagenPoster;

    @Column(name = "mes", nullable = false)
    private String mes; // El mes en el que debe aparecer el carrusel, puede ser algo como "Abril", "Mayo", etc.

    @Column(name = "anio", nullable = false)
    private int anio; // El año en el que debe aparecer el carrusel, por ejemplo 2025.

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
