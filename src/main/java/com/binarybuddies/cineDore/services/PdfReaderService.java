package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.*;
import com.binarybuddies.cineDore.repositories.*;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PdfReaderService {


    private final SalaRepository salaRepository;

    private final PeliculaRepository peliculaRepository;

    private final FormatoRepository formatoRepository;

    private final LenguajeRepository lenguajeRepository;

    private final ColorRepository colorRepository;

    // Inyección por constructor
    @Autowired
    public PdfReaderService(SalaRepository salaRepository, PeliculaRepository peliculaRepository, FormatoRepository formatoRepository, LenguajeRepository lenguajeRepository, ColorRepository colorRepository) {
        this.salaRepository = salaRepository;
        this.peliculaRepository = peliculaRepository;
        this.formatoRepository = formatoRepository;
        this.lenguajeRepository = lenguajeRepository;
        this.colorRepository = colorRepository;
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void readPdf() {

    }

    private void descargarPdf(String fileUrl, String destinationPath) throws IOException {
        URI uri = URI.create(fileUrl);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try(InputStream inputStream = connection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(destinationPath) ) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            throw new IOException(connection.getResponseMessage());
        }
    }


    public String procesarPdf(String filePath) throws IOException {

         try (PDDocument document = Loader.loadPDF(new File(filePath))) {
             PDFTextStripper stripper = new PDFTextStripper();
             String texto = stripper.getText(document);
             System.out.println(texto);
             return texto;

         }catch (FileNotFoundException e) {
             System.out.println("No se encontro el archivo en la ruta");
             return null;
         }
    }

    /**
     * Crear un objeto función DTO. Comprueba las fechas y crea uno por fecha y hora
     *
     *
     * @param texto Solicitud del texto del pdf parseado
     */
    @Transactional
    public void fechaFuncion(String texto) {
        Calendar fecha = Calendar.getInstance();

        Pattern mesPattern = Pattern.compile("(ENERO|FEBRERO|MARZO|ABRIL|MAYO|JUNIO|JULIO|AGOSTO|SEPTIEMBRE|OCTUBRE|NOVIEMBRE|DICIEMBRE)");
        Pattern anioPattern = Pattern.compile("\\d{4}");
        Pattern horaPattern = Pattern.compile("(\\d{2}:\\d{2})");
        Pattern bloqueDiaPattern = Pattern.compile(
                "(SÁBADO|DOMINGO|LUNES|MARTES|MIÉRCOLES|JUEVES|VIERNES)\\s+(\\d+).*?(?=(SÁBADO|DOMINGO|LUNES|MARTES|MIÉRCOLES|JUEVES|VIERNES)|$)",
                Pattern.DOTALL
        );

        // Establecer año
        Matcher anioMatcher = anioPattern.matcher(texto);
        if (anioMatcher.find()) {
            fecha.set(Calendar.YEAR, Integer.parseInt(anioMatcher.group()));
        }

        // Establecer mes
        Matcher mesMatcher = mesPattern.matcher(texto);
        if (mesMatcher.find()) {
            int mes = obtenerNumeroMes(mesMatcher.group(1));
            fecha.set(Calendar.MONTH, mes - 1);
        }

        Matcher bloqueDiaMatcher = bloqueDiaPattern.matcher(texto);

        while (bloqueDiaMatcher.find()) {
            String diaSemana = bloqueDiaMatcher.group(1);
            int diaNumero = Integer.parseInt(bloqueDiaMatcher.group(2));
            String bloque = bloqueDiaMatcher.group(0);
            fecha.set(Calendar.DAY_OF_MONTH, diaNumero);
            String[] lineas = bloque.split("\\R");

            Pattern autorPattern = Pattern.compile("([A-ZÁÉÍÓÚÑ.\\s]+),\\s*(\\d{4})"); // Autor y Año

            // Recorremos las líneas del bloque
            for (int i = 1; i < lineas.length; i++) {
                String linea = lineas[i].trim();
                Matcher autorMatcher = autorPattern.matcher(linea);

                if (autorMatcher.find()) {
                    String titulo = lineas[i - 1].trim();
                    int anio = Integer.parseInt(autorMatcher.group(2));

                    Pelicula pelicula = new Pelicula();
                    pelicula.setNombre(titulo);
                    pelicula.setAnio(anio);

                    // Buscar en línea anterior, actual y siguiente
                    StringBuilder contexto = new StringBuilder();
                    for (int j = Math.max(i - 1, 0); j <= Math.min(i + 2, lineas.length - 1); j++) {
                        contexto.append(lineas[j]).append(" ");
                    }
                    String datosPeliculas = contexto.toString();

                    Matcher duracionMatcher = Pattern.compile("(\\d+)[’']").matcher(datosPeliculas);
                    Matcher formatoMatcher = Pattern.compile("\\b(35 MM|16 MM|BDG|BSP|B-R|DCP|REST)\\b").matcher(datosPeliculas);
                    Matcher idiomaMatcher = Pattern.compile("\\b(VE|VOSE|VOSS|VOSI|VOSFR|MRE|MRI/E\\*|VOSE\\*)\\b").matcher(datosPeliculas);
                    Matcher colorMatcher = Pattern.compile("\\b(B/N|Color)\\b", Pattern.CASE_INSENSITIVE).matcher(datosPeliculas);
                    Matcher horaMatcher = horaPattern.matcher(datosPeliculas);
                    Matcher salaMatcher = Pattern.compile("SALA\\s+(\\d+)").matcher(datosPeliculas);

                    if (duracionMatcher.find()) pelicula.setDuracion(Integer.parseInt(duracionMatcher.group(1)));
                    if (formatoMatcher.find()) formatoRepository.getFormatoByNombre(formatoMatcher.group(1)).ifPresent(pelicula::setFormato);
                    if (idiomaMatcher.find()) lenguajeRepository.getLenguajesByNombre(idiomaMatcher.group(1)).ifPresent(pelicula::setLenguaje);
                    if (colorMatcher.find()) colorRepository.getColorByColor(colorMatcher.group(1)).ifPresent(pelicula::setColor);

                    if (horaMatcher.find() && salaMatcher.find()) {
                        String[] partesHora = horaMatcher.group(1).split(":");
                        fecha.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partesHora[0]));
                        fecha.set(Calendar.MINUTE, Integer.parseInt(partesHora[1]));
                        LocalDateTime fechaHora = fecha.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

                        System.out.println("🎬 Película: " + pelicula.getNombre());
                        System.out.println("📅 Año: " + pelicula.getAnio());
                        System.out.println("⏱️ Duración: " + pelicula.getDuracion());
                        System.out.println("📽️ Formato: " + (pelicula.getFormato() != null ? pelicula.getFormato().getNombre() : "N/A"));
                        System.out.println("🗣️ Idioma: " + (pelicula.getLenguaje() != null ? pelicula.getLenguaje().getNombre() : "N/A"));
                        System.out.println("🎨 Color: " + (pelicula.getColor() != null ? pelicula.getColor().getColor() : "N/A"));
                        System.out.println("🕒 Función: " + fechaHora);
                        System.out.println("🏛️ Sala: Sala " + salaMatcher.group(1));
                        System.out.println("──────────────────────────────");
                    }
                }
            }
        }
    }


    public static int obtenerNumeroMes(String mes) {
        return switch (mes.toUpperCase()) {
            case "ENERO" -> 1;
            case "FEBRERO" -> 2;
            case "MARZO" -> 3;
            case "ABRIL" -> 4;
            case "MAYO" -> 5;
            case "JUNIO" -> 6;
            case "JULIO" -> 7;
            case "AGOSTO" -> 8;
            case "SEPTIEMBRE" -> 9;
            case "OCTUBRE" -> 10;
            case "NOVIEMBRE" -> 11;
            case "DICIEMBRE" -> 12;
            default -> -1;  // Mes no válido
        };
    }

}
