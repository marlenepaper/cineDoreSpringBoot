// src/main/java/com/binarybuddies/cineDore/services/CarruselService.java
package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Carrusel;
import com.binarybuddies.cineDore.repositories.CarruselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.LocalDate;
import java.util.List;

@Service
public class CarruselService {

    @Autowired
    private CarruselRepository carruselRepository;

    public List<Carrusel> getCarruselDataForCurrentMonth() {
        // Obtener el mes y el año actuales
        String currentMonth = LocalDate.now().getMonth().toString(); // "APRIL", "MAY", etc.
        int currentYear = LocalDate.now().getYear();

        // Realizar la consulta para obtener los registros que coincidan con el mes y el año actuales
        return carruselRepository.findByMesAndAnio(currentMonth, currentYear);
    }
}
