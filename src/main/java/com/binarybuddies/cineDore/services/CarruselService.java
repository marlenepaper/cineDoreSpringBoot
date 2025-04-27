package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.CarruselDTO;
import com.binarybuddies.cineDore.models.Carrusel;
import com.binarybuddies.cineDore.repositories.CarruselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Servicio para gestionar los datos del carrusel de películas.
 */
@Service
public class CarruselService {

    @Autowired
    private CarruselRepository carruselRepository;
    /**
     * Obtiene los datos del carrusel correspondientes al mes y año actuales.
     * <p>
     * Consulta la base de datos por los registros del mes y año actuales,
     * los transforma en objetos {@link CarruselDTO} y los devuelve como una lista.
     * </p>
     *
     * @return Lista de {@link CarruselDTO} con los datos del carrusel para el mes actual.
     */
    public List<CarruselDTO> getCarruselDataForCurrentMonth() {
        String currentMonth = LocalDate.now().getMonth().toString();
        int currentYear = LocalDate.now().getYear();

        List<Carrusel> carruselList = carruselRepository.findByMesAndAnio(currentMonth, currentYear);

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
                .collect(Collectors.toList());
    }
}
