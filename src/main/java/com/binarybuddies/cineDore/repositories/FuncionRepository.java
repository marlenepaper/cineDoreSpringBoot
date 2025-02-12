package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    Optional<Funcion> getReferenceById(long id);
}
