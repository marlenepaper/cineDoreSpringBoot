package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Color;
import com.binarybuddies.cineDore.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Transactional
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    public Optional<Color> getColorById(long id) {
        return Optional.of(this.colorRepository.getById(id));
    }
}
