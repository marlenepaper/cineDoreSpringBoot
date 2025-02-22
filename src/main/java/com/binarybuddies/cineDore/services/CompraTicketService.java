package com.binarybuddies.cineDore.services;


import com.binarybuddies.cineDore.config.ResourceNotFoundException;
import com.binarybuddies.cineDore.dto.*;
import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.repositories.UsuarioRepository;
import com.binarybuddies.cineDore.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompraTicketService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public TicketDTO realizarCompra(CompraDTO request) {
        // if email already exists
        if (usuarioRepository.existsByCorreoElectronico(request.getCorreoElectronico())) {
            throw new RuntimeException("Email ya está registrado");
        }

        // Crear una nueva compra
        Compra compra = new Compra();
        compra.setNombre(request.getNombre());
        compra.setApellidos(request.getApellidos());
        compra.setCorreoElectronico(request.getCorreoElectronico());
        compra.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        compra.setTelefono(request.getTelefono());
        compra.setIdentificacion(request.getIdentificacion());
        compra.setFechaNacimiento(request.getFechaNacimiento());

        usuario = usuarioRepository.save(usuario);

        String token = jwtUtil.generateToken(usuario.getCorreoElectronico());
        return new AuthResponseDTO(token, usuario);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getCorreoElectronico());
        return new AuthResponseDTO(token, usuario);
    }

    public UserProfileDTO getUserProfile(String email) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        UserProfileDTO dto = new UserProfileDTO();
        dto.setNombre(usuario.getNombre());
        dto.setApellidos(usuario.getApellidos());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setTelefono(usuario.getTelefono());

        return dto;
    }


    public void deleteAccount(String email) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

}