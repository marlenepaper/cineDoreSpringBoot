package com.binarybuddies.cineDore.repositories;
import com.binarybuddies.cineDore.models.Links;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Links, Long> {

    Optional<Links> findById(Long id);
}
