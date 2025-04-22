package com.binarybuddies.cineDore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO para recibir los datos de registro del usuario
 */

@Data
public class UserRegisterRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @Email(message = "Email debe ser válido")
    @NotBlank(message = "Email es obligatorio")
    private String correoElectronico;

    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasenia;

    @Pattern(regexp = "^[0-9]*$", message = "El número telefónico solo puede contener números")
    private String telefono;

    @NotBlank(message = "Identificación es obligatoria")
    private String identificacion;

    private LocalDate fechaNacimiento;
}

