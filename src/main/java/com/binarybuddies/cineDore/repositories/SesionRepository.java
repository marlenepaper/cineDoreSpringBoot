package com.binarybuddies.cineDore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionRepository extends JpaRepository< SesionRepository, Long> {

    @Autowired
    private SesionRepository sesionRepository;

    public List<Sesion> getAllSesiones() {
        return sesionRepository.findAll();
    }
}
