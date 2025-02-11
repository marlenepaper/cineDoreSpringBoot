package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Funcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {
}
