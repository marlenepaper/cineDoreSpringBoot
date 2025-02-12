package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.TipoEntrada;
import com.binarybuddies.cineDore.services.TipoEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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
}
