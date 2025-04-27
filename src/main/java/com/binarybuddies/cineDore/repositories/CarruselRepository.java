package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Carrusel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarruselRepository extends JpaRepository<Carrusel, Long> {

    List<Carrusel> findByMesAndAnio(String mes, int anio);
}
