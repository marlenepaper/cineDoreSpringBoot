package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Clasificacion;
import com.binarybuddies.cineDore.services.ClasificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Clasificacion", description = "Operaciones con clasificaciones de peliculas")
@RestController
@RequestMapping("/clasificaciones")
@CrossOrigin(origins = "*")
public class ClasificacionController {

    @Autowired
    private ClasificacionService clasificacionService;

    @Operation(summary = "Obtener lista de clasificaciones")
    @ApiResponse(responseCode = "200", description = "Lista de clasificaciones encontrada")
    @GetMapping
    public ResponseEntity<List<Clasificacion>> getAllClasificaciones() {
        List<Clasificacion> clasificaciones = clasificacionService.getAll();
        return ResponseEntity.ok(clasificaciones);
    }

    @Operation(summary = "Obtener clasificacion por ID")
    @ApiResponse(responseCode = "200", description = "Clasificacion encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Clasificacion>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.clasificacionService.getClasificacionById(id));
    }
}
