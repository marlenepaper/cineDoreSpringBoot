package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Links;
import com.binarybuddies.cineDore.repositories.LinkRepository;
import com.binarybuddies.cineDore.utils.PdfDownloader;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class LinkService {

    private static final String WEB_URL = "https://www.cultura.gob.es/cultura/areas/cine/mc/fe/difusion/programa.html";
    private final PdfDownloader pdfDownloader;
    private final PdfReaderService pdfReaderService;
    private final LinkRepository linkRepository;

    public LinkService(PdfDownloader pdfDownloader, PdfReaderService pdfReaderService, LinkRepository linkRepository) {
        this.pdfDownloader = pdfDownloader;
        this.pdfReaderService = pdfReaderService;
        this.linkRepository = linkRepository;
    }

    @Scheduled(cron = "0 0 10 20-31 * *") // Todos los días del 20 al 31 a las 10:00 AM
    public void downloadAndReadPdf() throws IOException {
        //Directorio temporal donde se va a guardar el pdf
        Path destino = Paths.get("temp");
        //Crea el directorio si no existía
        Files.createDirectories(destino);

        //Descarga el archivo pdf y lo guarda en el directorio
        Optional<Path> archivo = pdfDownloader.downloadPdf(WEB_URL, destino);


        if (archivo.isPresent()) {

            //Busca el link en la base de datos, si existe, se termina
            Optional<Links> linkOpt = linkRepository.findLinksByUrl(archivo.get().getFileName().toString());
            if (linkOpt.isPresent()) {
                try {
                    Files.delete(archivo.get());
                } catch (IOException e) {
                    System.out.println("Error al eliminar el archivo");
                }
                return;
            }

            //Parsea el pdf a string
            String texto = pdfReaderService.procesarPdfpeliculas(archivo.get().toString());
            //Guarda las funciones y las películas del texto
            pdfReaderService.extraerDatosPeliculasFunciones(texto);

            //Crea una entidad link si no existe
            Links link = new Links();
            //Guarda la url del pdf
            link.setUrl(archivo.get().getFileName().toString());
            //El nombre de la entidad será el mes y el año de descarga
            LocalDateTime fechaDescarga = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
            String nombre = fechaDescarga.format(formatter);
            link.setNombre(nombre);

            //Lo guarda en la base de datos
            linkRepository.save(link);

            //Elimina el archivo
            try{
                Files.delete(archivo.get());
            }catch (IOException e){
                System.out.println("Error al eliminar el archivo");
            }

        }
    }
}
