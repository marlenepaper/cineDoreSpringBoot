package com.binarybuddies.cineDore.controllers;

import com.binarybuddies.cineDore.models.Cuenta;
import com.binarybuddies.cineDore.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "*")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<List<Cuenta>> getAllCuentas() {
        List<Cuenta> cuentas = cuentaService.getAll();
        return ResponseEntity.ok(cuentas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cuenta>> getById(@PathVariable long id) {
        return ResponseEntity.ok(this.cuentaService.getCuentaById(id));
    }
}
