package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.TicketEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface TicketEntradaRepository extends JpaRepository<TicketEntrada, Long> {

    Optional<TicketEntrada> getReferenceById(long id);
}
