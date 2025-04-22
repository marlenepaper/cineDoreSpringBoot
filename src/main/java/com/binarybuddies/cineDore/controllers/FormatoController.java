package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Formato;
import com.binarybuddies.cineDore.services.FormatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Tag(name= "Formatos", description = "Operaciones con formatos de peliculas")
@RestController
@RequestMapping("/formatos")
@CrossOrigin(origins = "*")
public class FormatoController {

    @Autowired
    private FormatoService formatoService;

    @Operation(summary = "Obtener lista de formatos de pelicula")
    @ApiResponse(responseCode = "200", description = "Lista de formatos encontrada")
    @GetMapping
    public ResponseEntity<List<Formato>> getAllFormatos() {
        List<Formato> formatos = formatoService.getAll();
        return ResponseEntity.ok(formatos);
    }

    @Operation(summary = "Obtener formato por ID")
    @ApiResponse(responseCode = "200", description = "Formato encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Formato>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.formatoService.getFormatoById(id));
    }
}
