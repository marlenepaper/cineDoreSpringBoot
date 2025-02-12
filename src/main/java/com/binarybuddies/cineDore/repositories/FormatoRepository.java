package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Formato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface FormatoRepository extends JpaRepository<Formato, Long> {

    Optional<Formato> getReferenceById(long id);
}
