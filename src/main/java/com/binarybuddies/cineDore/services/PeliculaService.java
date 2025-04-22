package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.dto.FuncionDTO;
import com.binarybuddies.cineDore.dto.PeliculaDTO;
import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.models.Pelicula;
import com.binarybuddies.cineDore.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PeliculaService {
    @Autowired
    private PeliculaRepository peliculaRepository;

    public List<PeliculaDTO> getAll() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        return peliculas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public Optional<PeliculaDTO> getPeliculaById(long id) {
        return peliculaRepository.findById(id).map(this::convertToDTO);
    }

    /**
     * Generar una PeliculaDTO con su respectiva FuncionDTO
     *
     * @param pelicula Una pelicula
     * @return Un DTO de pelicula
     */

    private PeliculaDTO convertToDTO(Pelicula pelicula) {
        return new PeliculaDTO(
                pelicula.getId(),
                pelicula.getNombre(),
                pelicula.getAnio(),
                pelicula.getDuracion(),
                pelicula.getSinopsis(),
                pelicula.getImagenPoster(),
                pelicula.getCategoria().getNombre(),
                pelicula.getClasificacion().getNombre(),
                pelicula.getFormato().getDetalle(),
                pelicula.getLenguaje().getNombre(),
                pelicula.getColor().getColor(),
                pelicula.getFunciones().stream()
                        .map(this::convertFuncionToDTO)
                        .collect(Collectors.toList())
        );
    }

    /**
     * Genera una funcionDTO
     *
     * @param funcion Una función
     * @return Un DTO de función
     */

    private FuncionDTO convertFuncionToDTO(Funcion funcion) {
        return new FuncionDTO(
                funcion.getId(),
                funcion.getFechaHora().toString(),
                funcion.getSala().getNombre()
        );
    }
}