package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping
    public ResponseEntity<List<Compra>> getAllCompras() {
        List<Compra> compras = compraService.getAll();
        return ResponseEntity.ok(compras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Compra>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.compraService.getCompraById(id));
    }
}
