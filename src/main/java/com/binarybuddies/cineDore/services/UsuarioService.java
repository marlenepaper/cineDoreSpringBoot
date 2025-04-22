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
        return this.usuarioRepository.getReferenceById(id);
    }

    /**
     * Registro del usuario
     *
     * @param request Solicitud de datos del usuario
     * @return UserRegisterResponseDTO, se crea el usuario con un token
     * @throws IllegalArgumentException si el email ya está registrado
     */
    @Transactional
    public UserRegisterResponseDTO register(UserRegisterRequestDTO request) {
        if (usuarioRepository.existsByCorreoElectronico(request.getCorreoElectronico())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        //Se crea un objeto usuario y se guarda en la base de datos
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
    /**
     * Actualización de datos del usuario
     *
     * @param userDTO Solicitud de datos del usuario
     * @param id El id del usuario
     * @return Usuario con los datos nuevos
     * @throws RuntimeException si no encuentra el usuario
     */
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

    /**
     * Login del usuario
     *
     * @param request Solicitud de credenciales
     * @return UserRegisterResponseDTO con un nuevo token
     * @throws RuntimeException contraseña incorrecta
     */
    public UserRegisterResponseDTO login(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getContrasenia(), usuario.getContrasenia())) {
            throw new RuntimeException("Contraseña incorrecta");
        }
        String token = jwtUtil.generateToken(usuario.getCorreoElectronico());
        return new UserRegisterResponseDTO(token, usuario);
    }

    /**
     * Traer datos del usuario
     *
     * @param email Solicitud del correo
     * @return UserDTO con los datos necesarios
     * @throws ResourceNotFoundException no encuentra el usuario
     */
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

    /**
     * Borrar cuenta del usuario
     *
     * @param email Solicitud del correo
     * @throws ResourceNotFoundException no encuentra el usuario
     */
    public void deleteAccount(String email) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }
}
