package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Cine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CineRepository extends JpaRepository<Cine, Long> {

    Optional<Cine> getReferenceById(long id);
}
