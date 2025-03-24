package com.binarybuddies.cineDore.dto;

import com.binarybuddies.cineDore.models.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisterResponseDTO {
    private Long id;
    private String nombre;
    private String apellidos;
    private String correoElectronico;
    private String telefono;
    private String identificacion;
    private LocalDate fechaNacimiento;
    private String token;



    public UserRegisterResponseDTO(String token, Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        this.correoElectronico = usuario.getCorreoElectronico();
        this.telefono = usuario.getTelefono();
        this.identificacion = usuario.getIdentificacion();
        this.fechaNacimiento = usuario.getFechaNacimiento();
        this.token = token;

    }
}
