package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }
}
