package com.binarybuddies.cineDore.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "correo_electronico", unique = true, nullable = false)
    @Email(message = "Email should be valid")
    private String correoElectronico;

    @Column(name = "contraseña", nullable = false)
    @NotNull(message = "Contraseña no puede estar vacía")
    private String contrasenia;

    @Column(name = "telefono")
    @Pattern(regexp = "^[0-9]*$", message = "El numero telefonico unicamente puede contenter numeros")
    private String telefono;

    @Column(unique = true, nullable = false)
    private String identificacion;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cuenta cuenta;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Compra> compras= new ArrayList<>();
}
