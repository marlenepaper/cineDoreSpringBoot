package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Compra;
import com.binarybuddies.cineDore.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
