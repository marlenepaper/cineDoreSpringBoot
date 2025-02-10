package com.binarybuddies.cineDore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository< PeliculaRepository, Long> {
}
