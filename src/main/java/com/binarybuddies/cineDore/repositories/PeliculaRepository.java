package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    Optional<Pelicula> getReferenceById(long id);
}
