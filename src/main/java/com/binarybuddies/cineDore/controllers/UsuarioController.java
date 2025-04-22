package com.binarybuddies.cineDore.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name= "Usuarios", description = "Operaciones con usuarios")
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Obtener lista de usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios encontrada")
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @Operation(summary = "Obtener usuario por ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> getUsuarioById(@PathVariable long id) {
        return ResponseEntity.ok(this.usuarioService.getUsuarioById(id));
    }

    @Operation(summary = "Crear usuario con datos solicitados")
    @ApiResponse(responseCode = "200", description = "Usuario creado correctamente")
    @ApiResponse(responseCode = "409", description = "El usuario ya existe")
    @ApiResponse(responseCode = "500", description = "Error interno al registar usuario")
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

    @Operation(summary = "Actualizar datos de usuario por su id y datos anteriores")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @ApiResponse(responseCode = "500", description = "Error interno al actualizar usuario")
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

    @Operation(summary = "Login con credenciales")
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PostMapping("/login")
    public ResponseEntity<UserRegisterResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        return ResponseEntity.ok(usuarioService.login(request));
    }

    @Operation(summary = "Obtener datos de usuario con autenticacion")
    @ApiResponse(responseCode = "200", description = "Datos de usuario encontrados")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(Authentication authentication) {
        String email = authentication.getName();
        UserDTO profile = usuarioService.getUserProfile(email);
        return ResponseEntity.ok(profile);
    }

    @Operation(summary = "Eliminar cuenta de usuario con autenticacion")
    @ApiResponse(responseCode = "200", description = "Cuenta eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(Authentication authentication) {
        String email = authentication.getName();
        usuarioService.deleteAccount(email);
        return ResponseEntity.ok("Cuenta eliminada exitosamente");
    }


}
