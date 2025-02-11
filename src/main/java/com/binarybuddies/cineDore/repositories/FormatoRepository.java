package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Formato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormatoRepository extends JpaRepository<Formato, Long> {
}
