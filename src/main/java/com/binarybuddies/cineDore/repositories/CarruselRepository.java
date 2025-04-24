package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Carrusel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarruselRepository extends JpaRepository<Carrusel, Long> {

    // Método para obtener los elementos del carrusel que coinciden con el mes y año actuales
    List<Carrusel> findByMesAndAnio(String mes, int anio);
}
