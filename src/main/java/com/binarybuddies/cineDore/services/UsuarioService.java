package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.config.ResourceNotFoundException;
import com.binarybuddies.cineDore.dto.LoginRequestDTO;
import com.binarybuddies.cineDore.dto.UserDTO;
import com.binarybuddies.cineDore.dto.UserRegisterRequestDTO;
import com.binarybuddies.cineDore.dto.UserRegisterResponseDTO;
import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.repositories.UsuarioRepository;
import com.binarybuddies.cineDore.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> getUsuarioById(long id) {
        return Optional.of(this.usuarioRepository.getById(id));
    }

    @Transactional
    public UserRegisterResponseDTO register(UserRegisterRequestDTO request) {
        if (usuarioRepository.existsByCorreoElectronico(request.getCorreoElectronico())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
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
        return new UserRegisterResponseDTO(token, usuario);
    }

    public Usuario updateUser(UserDTO userDTO, Long id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioExistente.setNombre(userDTO.getNombre());
        usuarioExistente.setApellidos(userDTO.getApellidos());
        usuarioExistente.setCorreoElectronico(userDTO.getCorreoElectronico());
        usuarioExistente.setTelefono(userDTO.getTelefono());
        usuarioExistente.setIdentificacion(userDTO.getIdentificacion());
        usuarioExistente.setFechaNacimiento(userDTO.getFechaNacimiento());
        if (userDTO.getContrasenia() != null && !userDTO.getContrasenia().isEmpty()) {
            usuarioExistente.setContrasenia(passwordEncoder.encode(userDTO.getContrasenia()));
        }
        return usuarioRepository.save(usuarioExistente);
    }

    public UserRegisterResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        String token = jwtUtil.generateToken(usuario.getCorreoElectronico());
        return new UserRegisterResponseDTO(token, usuario);
    }


    public UserDTO getUserProfile(String email) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        UserDTO dto = new UserDTO();
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
