package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.CarruselDTO;
import com.binarybuddies.cineDore.models.Carrusel;
import com.binarybuddies.cineDore.repositories.CarruselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarruselService {

    @Autowired
    private CarruselRepository carruselRepository;

    public List<CarruselDTO> getCarruselDataForCurrentMonth() {
        // Obtener el mes y el año actuales
        String currentMonth = LocalDate.now().getMonth().toString(); // "APRIL", "MAY", etc.
        int currentYear = LocalDate.now().getYear();

        // Obtener la lista de Carrusel desde el repositorio
        List<Carrusel> carruselList = carruselRepository.findByMesAndAnio(currentMonth, currentYear);

        // Convertir la lista de Carrusel a CarruselDTO
        return carruselList.stream()
                .map(carrusel -> {
                    CarruselDTO carruselDTO = new CarruselDTO();
                    carruselDTO.setId(carrusel.getId());
                    carruselDTO.setTitulo(carrusel.getTitulo());
                    carruselDTO.setSubtitulo(carrusel.getSubtitulo());
                    carruselDTO.setImagenPoster(carrusel.getImagenPoster());
                    carruselDTO.setMes(carrusel.getMes());
                    carruselDTO.setAnio(carrusel.getAnio());
                    return carruselDTO;
                })
                .collect(Collectors.toList()); // Devuelve la lista de CarruselDTO
    }
}
