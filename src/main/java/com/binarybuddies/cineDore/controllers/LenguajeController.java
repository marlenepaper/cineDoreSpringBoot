package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Lenguaje;
import com.binarybuddies.cineDore.services.LenguajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Tag(name= "Lenguajes", description = "Operaciones con lenguajes de peliculas")
@RestController
@RequestMapping("/lenguajes")
@CrossOrigin(origins = "*")
public class LenguajeController {

    @Autowired
    private LenguajeService lenguajeService;

    @Operation(summary = "Obtener lista de lenguajes de pelicula")
    @ApiResponse(responseCode = "200", description = "Lista de lenguajes encontrada")
    @GetMapping
    public ResponseEntity<List<Lenguaje>> getAllLenguajes() {
        List<Lenguaje> lenguajes = lenguajeService.getAll();
        return ResponseEntity.ok(lenguajes);
    }

    @Operation(summary = "Obtener lenguaje por ID")
    @ApiResponse(responseCode = "200", description = "Lenguaje encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Lenguaje>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.lenguajeService.getLenguajeById(id));
    }
}
