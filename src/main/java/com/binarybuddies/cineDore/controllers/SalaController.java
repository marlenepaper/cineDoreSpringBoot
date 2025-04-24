package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Sala;
import com.binarybuddies.cineDore.services.SalaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Salas", description = "Operaciones con salas")
@RestController
@RequestMapping("/salas")
@CrossOrigin(origins = "*")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @Operation(summary = "Obtener lista de salas")
    @ApiResponse(responseCode = "200", description = "Lista de salas encontrada")
    @GetMapping
    public ResponseEntity<List<Sala>> getAllSalas() {
        List<Sala> salas = salaService.getAll();
        return ResponseEntity.ok(salas);
    }

    @Operation(summary = "Obtener sala por ID")
    @Parameter(name = "id", required = true)
    @ApiResponse(responseCode = "200", description = "Sala encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Sala>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.salaService.getSalaById(id));
    }
}
