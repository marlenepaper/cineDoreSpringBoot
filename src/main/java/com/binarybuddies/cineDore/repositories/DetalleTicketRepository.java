package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.DetalleTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface DetalleTicketRepository extends JpaRepository<DetalleTicket, Long> {

    Optional<DetalleTicket> getReferenceById(long id);
}
