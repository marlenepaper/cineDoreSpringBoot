package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
}
