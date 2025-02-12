package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.TipoEntrada;
import com.binarybuddies.cineDore.services.TipoEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tipos_entrada")
@CrossOrigin(origins = "*")
public class TipoEntradaController {

    @Autowired
    private TipoEntradaService tipoEntradaService;

    @GetMapping
    public ResponseEntity<List<TipoEntrada>> getAllTiposEntrada() {
        List<TipoEntrada> tiposEntrada = tipoEntradaService.getAll();
        return ResponseEntity.ok(tiposEntrada);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<TipoEntrada>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.tipoEntradaService.getTipoEntradaById(id));
    }
}
