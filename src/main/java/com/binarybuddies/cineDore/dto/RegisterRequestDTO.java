package com.binarybuddies.cineDore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RegisterRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Email(message = "Email debe ser válido")
    @NotBlank(message = "Email es obligatorio")
    private String correoElectronico;

    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasenia;

    private String telefono;

    @NotBlank(message = "Identificación es obligatoria")
    private String identificacion;

    private LocalDate fechaNacimiento;
}

