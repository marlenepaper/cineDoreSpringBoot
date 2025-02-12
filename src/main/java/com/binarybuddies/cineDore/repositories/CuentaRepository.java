package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Optional<Cuenta> getReferenceById(long id);
}
