package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.services.FuncionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Funciones", description = "Operaciones con funciones")
@RestController
@RequestMapping("/funciones")
@CrossOrigin(origins = "*")
public class FuncionController {

    @Autowired
    private FuncionService funcionService;

    @Operation(summary = "Obtener lista de funciones")
    @ApiResponse(responseCode = "200", description = "Lista de funciones encontrada")
    @GetMapping
    public ResponseEntity<List<Funcion>> getAllFunciones() {
        List<Funcion> funciones = funcionService.getAll();
        return ResponseEntity.ok(funciones);
    }

    @Operation(summary = "Obtener funcion por ID")
    @ApiResponse(responseCode = "200", description = "Funcion encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Funcion>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.funcionService.getFuncionById(id));
    }
}
