package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Clasificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ClasificacionRepository extends JpaRepository<Clasificacion, Long> {

    Optional<Clasificacion> getReferenceById(long id);
}
