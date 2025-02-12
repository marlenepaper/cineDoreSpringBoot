package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Lenguaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface LenguajeRepository extends JpaRepository<Lenguaje, Long> {

    Optional<Lenguaje> getReferenceById(long id);
}
