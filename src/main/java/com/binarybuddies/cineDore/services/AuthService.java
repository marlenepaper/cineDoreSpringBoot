package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.config.ResourceNotFoundException;
import com.binarybuddies.cineDore.dto.AuthResponseDTO;
import com.binarybuddies.cineDore.dto.LoginRequestDTO;
import com.binarybuddies.cineDore.dto.RegisterRequestDTO;
import com.binarybuddies.cineDore.dto.UserProfileDTO;
import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.repositories.UsuarioRepository;
import com.binarybuddies.cineDore.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public AuthResponseDTO register(RegisterRequestDTO request) {
        // if email already exists
        if (usuarioRepository.existsByCorreoElectronico(request.getCorreoElectronico())) {
            throw new RuntimeException("Email ya está registrado");
        }

        // Create new user
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellidos(request.getApellidos());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setContrasenia(passwordEncoder.encode(request.getContrasenia()));
        usuario.setTelefono(request.getTelefono());
        usuario.setIdentificacion(request.getIdentificacion());
        usuario.setFechaNacimiento(request.getFechaNacimiento());

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
