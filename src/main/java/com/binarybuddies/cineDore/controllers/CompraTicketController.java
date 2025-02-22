package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.AuthResponseDTO;
import com.binarybuddies.cineDore.dto.LoginRequestDTO;
import com.binarybuddies.cineDore.dto.RegisterRequestDTO;
import com.binarybuddies.cineDore.dto.UserProfileDTO;
import com.binarybuddies.cineDore.services.AuthService;
import com.binarybuddies.cineDore.services.CompraTicketService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/compraTicket")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CompraTicketController {

    private final CompraTicketService compraTicketService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid RegisterRequestDTO request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // (optional)
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        // Add token to a blacklist
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getUserProfile(Authentication authentication) {
        String email = authentication.getName(); // Gets email from JWT token
        UserProfileDTO profile = authService.getUserProfile(email);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/delete-account")
    public ResponseEntity<String> deleteAccount(Authentication authentication) {
        String email = authentication.getName();
        authService.deleteAccount(email);
        return ResponseEntity.ok("Cuenta eliminada exitosamente");
    }

}