package com.binarybuddies.cineDore.dto;

import com.binarybuddies.cineDore.models.Usuario;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String correoElectronico;
    private String nombre;
    private Long id;

    public AuthResponseDTO(String token, Usuario usuario) {
        this.token = token;
        this.correoElectronico = usuario.getCorreoElectronico();
        this.nombre = usuario.getNombre();
        this.id = usuario.getId();
    }
}
