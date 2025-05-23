package com.binarybuddies.cineDore.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * DTO para recibir los credenciales para el login
 */

@Data
public class LoginRequestDTO {
    @Email(message = "Email debe ser válido")
    @NotBlank(message = "Email es obligatorio")
    private String correoElectronico;

    @NotBlank(message = "Contraseña es obligatoria")
    private String contrasenia;
}
