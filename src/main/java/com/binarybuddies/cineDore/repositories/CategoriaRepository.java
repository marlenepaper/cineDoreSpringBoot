package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> getReferenceById(long id);
}
