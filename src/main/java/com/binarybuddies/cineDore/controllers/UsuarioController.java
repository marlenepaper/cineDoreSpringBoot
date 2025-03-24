package com.binarybuddies.cineDore.controllers;

import org.springframework.security.core.Authentication;
import com.binarybuddies.cineDore.dto.LoginRequestDTO;
import com.binarybuddies.cineDore.dto.UserDTO;
import com.binarybuddies.cineDore.dto.UserRegisterRequestDTO;
import com.binarybuddies.cineDore.dto.UserRegisterResponseDTO;
import com.binarybuddies.cineDore.models.Usuario;
import com.binarybuddies.cineDore.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable long id) {
        return ResponseEntity.ok(this.usuarioService.getUsuarioById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUsuario(@RequestBody @Valid UserRegisterRequestDTO request) {
        try {
            return ResponseEntity.ok(this.usuarioService.register(request)); // 200 OK si todo está bien
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "El usuario ya existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse); // 409 Conflict
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Error inesperado");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse); // 500 Internal Server Error
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateUsuario(@RequestBody @Valid UserDTO userDTO, @PathVariable Long id) {
        try {
            Usuario updatedUser = usuarioService.updateUser(userDTO, id);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el usuario");
        }
    }


    @PostMapping("/login")
    public ResponseEntity<UserRegisterResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        UserDTO profile = usuarioService.getUserProfile(email);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(Authentication authentication) {
        String email = authentication.getName();
        usuarioService.deleteAccount(email);
        return ResponseEntity.ok("Cuenta eliminada exitosamente");
    }


}
