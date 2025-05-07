package com.binarybuddies.cineDore.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * Clase para descargar el pdf de la página de Cine Doré.
 */
@Component
public class PdfDownloader {

    /**
     * Crea una ruta donde se guarda el pdf descargado. Si encuentra varios, descarga el primero
     *
     * @param url La ruta de la página web
     * @param destino La ruta donde vamos a guardar el pdf descargado
     * @return Devuelve una ruta si está bien. Si no, devuelve vacío
     * @throws RuntimeException si la url da error
     */
    public Optional<Path> downloadPdf(String url, Path destino) {
        try{
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");

            for (Element link : links) {
                String href = link.attr("href").toLowerCase();
                String text = link.text().toLowerCase();

                if (href.endsWith("pdf") || text.contains("programa")){
                    String pdfUrl = link.absUrl("href");
                    String fileName = pdfUrl.substring(pdfUrl.lastIndexOf("/")+1);
                    Path destinoFinal = destino.resolve(fileName);

                    try(InputStream in = new URI(pdfUrl).toURL().openStream()){
                        Files.copy(in, destinoFinal, StandardCopyOption.REPLACE_EXISTING);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    return Optional.of(destinoFinal);
                }
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }
}
