package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Color;
import com.binarybuddies.cineDore.services.ColorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Colores", description = "Operaciones con colores de peliculas")
@RestController
@RequestMapping("/colores")
@CrossOrigin(origins = "*")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @Operation(summary = "Obtener lista de colores")
    @ApiResponse(responseCode = "200", description = "Lista de colores encontrada")
    @GetMapping
    public ResponseEntity<List<Color>> getAllColores() {
        List<Color> colores = colorService.getAll();
        return ResponseEntity.ok(colores);
    }

    @Operation(summary = "Obtener color por ID")
    @ApiResponse(responseCode = "200", description = "Color encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Color>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.colorService.getColorById(id));
    }
}
