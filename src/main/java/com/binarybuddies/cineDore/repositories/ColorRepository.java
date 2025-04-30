package com.binarybuddies.cineDore.repositories;

import com.binarybuddies.cineDore.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    Optional<Color> getReferenceById(long id);
    Optional<Color> getColorByColor(String color);
}
