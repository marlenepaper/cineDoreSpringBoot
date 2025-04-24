package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.TipoEntrada;
import com.binarybuddies.cineDore.services.TipoEntradaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Tipos de entrada", description = "Operaciones con tipos de entrada")
@RestController
@RequestMapping("/tipos_entrada")
@CrossOrigin(origins = "*")
public class TipoEntradaController {

    @Autowired
    private TipoEntradaService tipoEntradaService;

    @Operation(summary = "Obtener lista de tipos de entrada")
    @ApiResponse(responseCode = "200", description = "Lista de tipos de entrada encontrada")
    @GetMapping
    public ResponseEntity<List<TipoEntrada>> getAllTiposEntrada() {
        List<TipoEntrada> tiposEntrada = tipoEntradaService.getAll();
        return ResponseEntity.ok(tiposEntrada);
    }

    @Operation(summary = "Obtener tipo de entrada por ID")
    @Parameter(name = "id", required = true)
    @ApiResponse(responseCode = "200", description = "Tipo de entrada encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TipoEntrada>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.tipoEntradaService.getTipoEntradaById(id));
    }
}
