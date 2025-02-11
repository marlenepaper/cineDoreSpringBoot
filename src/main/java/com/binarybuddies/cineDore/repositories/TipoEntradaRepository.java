package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.TipoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoEntradaRepository extends JpaRepository<TipoEntrada, Long> {
}
