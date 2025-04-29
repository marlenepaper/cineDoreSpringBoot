package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.Funcion;
import com.binarybuddies.cineDore.models.Pelicula;
import com.binarybuddies.cineDore.models.Sala;
import com.binarybuddies.cineDore.repositories.SalaRepository;
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
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PdfReaderService {


    private final SalaRepository salaRepository;

    // Inyección por constructor
    @Autowired
    public PdfReaderService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
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
    public void fechaFuncion(String texto){
        //Se crea una instancia fecha
        Calendar fecha = Calendar.getInstance();

        //Patrones para detectar, dias, meses, años y horas
        Pattern mesPattern = Pattern.compile("(ENERO|FEBRERO|MARZO|ABRIL|MAYO|JUNIO|JULIO|AGOSTO|SEPTIEMBRE|OCTUBRE|NOVIEMBRE|DICIEMBRE)");
        Pattern horaPattern = Pattern.compile("(\\d{2}:\\d{2})");
        Pattern anioPattern = Pattern.compile("\\d{4}");

        //Divide el dia en bloques para sacar las diferentes horas de las funciones
        Pattern bloqueDiaPattern = Pattern.compile(
                "(SÁBADO|DOMINGO|LUNES|MARTES|MIÉRCOLES|JUEVES|VIERNES)\\s+(\\d+).*?(?=(SÁBADO|DOMINGO|LUNES|MARTES|MIÉRCOLES|JUEVES|VIERNES)|$)",
                Pattern.DOTALL | Pattern.CASE_INSENSITIVE
        );


        //Si encuentra un año en el pdf, devuelve el primero
        Matcher anioMatcher = anioPattern.matcher(texto);
        if (anioMatcher.find()) {
            System.out.println("Año" + anioMatcher.group(0));
            fecha.set(Calendar.YEAR, Integer.parseInt(anioMatcher.group(0)));
        }

        //Si encuentra un mes en el pdf, devuelve el primero. Lo convierte a num para sacar del dato de fecha
        Matcher mesMatcher = mesPattern.matcher(texto);
        if (mesMatcher.find()) {
            System.out.println("Mes número: " + obtenerNumeroMes(mesMatcher.group(0)));
            int mes = obtenerNumeroMes(mesMatcher.group(1));
            fecha.set(Calendar.MONTH, mes - 1);
        }

        Matcher bloqueDiaMatcher = bloqueDiaPattern.matcher(texto);

        //Comprueba el día de la semana
        while (bloqueDiaMatcher.find()) {
            String diaSemana = bloqueDiaMatcher.group(1);
            int diaNumero = Integer.parseInt(bloqueDiaMatcher.group(2));
            String bloque = bloqueDiaMatcher.group(0);

            System.out.println("▶ Día detectado: " + diaSemana + " " + diaNumero);
            fecha.set(Calendar.DAY_OF_MONTH, diaNumero);

            Matcher horaMatcher = horaPattern.matcher(bloque);
            while (horaMatcher.find()) {
                String horaStr = horaMatcher.group(1);
                String[] partesHora = horaStr.split(":");
                int h = Integer.parseInt(partesHora[0]);
                int m = Integer.parseInt(partesHora[1]);
                fecha.set(Calendar.HOUR_OF_DAY, h);
                fecha.set(Calendar.MINUTE, m);

                Date fechaFinal = fecha.getTime();

                // Formatear la fecha en formato yyyy-MM-dd
                ZoneId zona = ZoneId.systemDefault(); // o ZoneId.of("Europe/Madrid") si quieres especificar
                LocalDateTime fechaHora = fechaFinal.toInstant().atZone(zona).toLocalDateTime();
                System.out.println(fechaHora);

                //Crear un DTO de la función y asociar la fecha
                Funcion funcion = new Funcion();
                funcion.setFechaHora(fechaHora);


                Pattern salaPattern = Pattern.compile("SALA\\s+(\\d+)");
                Matcher salaMatcher = salaPattern.matcher(bloque);
                while (salaMatcher.find()) {

                    try{
                        Optional<Sala> sala = this.salaRepository.getSalaByNombre("Sala " + salaMatcher.group(1));
                        sala.ifPresent(funcion::setSala);
                        System.out.println("sala existente");
                    }catch (Exception e){
                        System.out.println("Sala no encontrada");
                    }

                }

                Pelicula pelicula = new Pelicula();



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
