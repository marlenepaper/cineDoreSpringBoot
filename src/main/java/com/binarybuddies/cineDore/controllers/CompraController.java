package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.dto.CompraDTO;
import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.services.CompraService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name= "Compra", description = "Operaciones con compras del usuario")
@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Operation(summary = "Obtener lista de compras")
    @ApiResponse(responseCode = "200", description = "Lista de compras encontrada")
    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.getAll();
        return ResponseEntity.ok(compras);
    }

    @Operation(summary = "Obtener compra por ID")
    @ApiResponse(responseCode = "200", description = "Compra encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compra>> getById(@PathVariable long id) {
        Optional<Compra> compra = compraService.getCompraById(id);
        return ResponseEntity.ok(compra);
    }

    @Operation(summary = "Crear compra")
    @ApiResponse(responseCode = "201", description = "Compra creada")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @ApiResponse(responseCode = "500", description = "Error interno al crear la compra")
    @PostMapping("/crear")
    public ResponseEntity<Compra> crearCompra(@RequestBody CompraDTO compraDTO) throws Exception {
        Compra nuevaCompra = compraService.crearCompra(compraDTO);
        return ResponseEntity.ok(nuevaCompra);
    }
}
