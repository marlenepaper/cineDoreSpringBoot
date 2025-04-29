package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

    Optional<Sala> getReferenceById(long id);

    Optional<Sala> getSalaByNombre(String nombre);
}
