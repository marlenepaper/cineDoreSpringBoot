package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name="estados_ticket")
public class EstadoTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

}


