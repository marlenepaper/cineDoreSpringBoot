package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {
}
