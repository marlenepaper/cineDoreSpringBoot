package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.models.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {

    Optional<Funcion> getReferenceById(long id);

    boolean existsFuncionByFechaHoraAndSala(LocalDateTime fechaHora, Sala sala);
}
