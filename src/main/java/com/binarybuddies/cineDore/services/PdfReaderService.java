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

    //Patrones estáticos
    private static final Pattern PATRON_AUTOR_ANIO = Pattern.compile("([A-ZÁÉÍÓÚÑ.\\s]+),\\s*(\\d{4})");
    private static final Pattern PATRON_MES = Pattern.compile("(ENERO|FEBRERO|MARZO|ABRIL|MAYO|JUNIO|JULIO|AGOSTO|SEPTIEMBRE|OCTUBRE|NOVIEMBRE|DICIEMBRE)");
    private static final Pattern PATRON_ANIO = Pattern.compile("\\d{4}");
    private static final Pattern PATRON_HORA = Pattern.compile("(\\d{2}:\\d{2})");
    private static final Pattern PATRON_BLOQUE_DIA = Pattern.compile(
            "(SÁBADO|DOMINGO|LUNES|MARTES|MIÉRCOLES|JUEVES|VIERNES)\\s+(\\d+).*?(?=(SÁBADO|DOMINGO|LUNES|MARTES|MIÉRCOLES|JUEVES|VIERNES)|$)",
            Pattern.DOTALL
    );
    private static final Pattern PATRON_DURACION = Pattern.compile("(\\d+)[’']");
    private static final Pattern PATRON_FORMATO = Pattern.compile("\\b(35 MM|16 MM|BDG|BSP|B-R|DCP|REST)\\b");
    private static final Pattern PATRON_IDIOMA = Pattern.compile("\\b(VE|VOSE|VOSS|VOSI|VOSFR|MRE|MRI/E\\*|VOSE\\*)\\b");
    private static final Pattern PATRON_COLOR = Pattern.compile("\\b(B/N|Color)\\b", Pattern.CASE_INSENSITIVE);
    private static final Pattern PATRON_SALA = Pattern.compile("SALA\\s+(\\d+)");
    private final SalaRepository salaRepository;

    private final PeliculaRepository peliculaRepository;

    private final FormatoRepository formatoRepository;

    private final LenguajeRepository lenguajeRepository;

    private final ColorRepository colorRepository;

    private final FuncionRepository funcionRepository;

    // Inyección por constructor
    @Autowired
    public PdfReaderService(SalaRepository salaRepository, PeliculaRepository peliculaRepository, FormatoRepository formatoRepository, LenguajeRepository lenguajeRepository, ColorRepository colorRepository, FuncionRepository funcionRepository) {
        this.salaRepository = salaRepository;
        this.peliculaRepository = peliculaRepository;
        this.formatoRepository = formatoRepository;
        this.lenguajeRepository = lenguajeRepository;
        this.colorRepository = colorRepository;
        this.funcionRepository = funcionRepository;
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


    public String procesarPdfpeliculas(String filePath) throws IOException {

         try (PDDocument document = Loader.loadPDF(new File(filePath))) {
             PDFTextStripper stripper = new PDFTextStripper();

             // Establecer el rango de páginas (1 es la primera página, 6 es la última página que queremos leer)
             stripper.setStartPage(1);  // Primera página
             stripper.setEndPage(6);    // Última página a leer

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

        // Establecer año
        Matcher anioMatcher = PATRON_ANIO.matcher(texto);
        if (anioMatcher.find()) {
            fecha.set(Calendar.YEAR, Integer.parseInt(anioMatcher.group()));
        }

        // Establecer mes
        Matcher mesMatcher = PATRON_MES.matcher(texto);
        if (mesMatcher.find()) {
            int mes = obtenerNumeroMes(mesMatcher.group(1));
            fecha.set(Calendar.MONTH, mes - 1);
        }

        Matcher bloqueDiaMatcher = PATRON_BLOQUE_DIA.matcher(texto);

        while (bloqueDiaMatcher.find()) {
            int diaNumero = Integer.parseInt(bloqueDiaMatcher.group(2));
            String bloque = bloqueDiaMatcher.group(0);
            fecha.set(Calendar.DAY_OF_MONTH, diaNumero);
            String[] lineas = bloque.split("\\R");


            // Recorremos las líneas del bloque
            for (int i = 1; i < lineas.length; i++) {
                String linea = lineas[i].trim();
                Matcher autorMatcher = PATRON_AUTOR_ANIO.matcher(linea);

                //Buscar autor y fecha
                if (autorMatcher.find()) {
                    String titulo = lineas[i - 1].trim();
                    int anio = Integer.parseInt(autorMatcher.group(2));

                    //Crear nueva funcion
                    Funcion funcion = new Funcion();

                    StringBuilder contexto = new StringBuilder();
                    int min = Math.min(i + 2, lineas.length - 1);
                    for (int j = Math.max(i - 2, 0); j <= min; j++) {
                        contexto.append(lineas[j]).append(" ");
                    }
                    String datosContexto = contexto.toString();

                    Matcher horaMatcher = PATRON_HORA.matcher(datosContexto);
                    if (horaMatcher.find()) {
                        String[] partesHora = horaMatcher.group(1).split(":");
                        fecha.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partesHora[0]));
                        fecha.set(Calendar.MINUTE, Integer.parseInt(partesHora[1]));
                        LocalDateTime fechaHora = fecha.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                        //Asignar fecha y hora  ala función
                        funcion.setFechaHora(fechaHora);
                    }

                    Matcher salaMatcher = PATRON_SALA.matcher(datosContexto);

                    //Buscar si la sala existe y asginarla a la función
                    if (salaMatcher.find()) {
                        Optional<Sala> sala = salaRepository.getSalaByNombre("Sala " + salaMatcher.group(1));
                        sala.ifPresent(funcion::setSala);
                    }
                    //Comprobar si existe la pelicula
                    Optional<Pelicula> peliculaOpt = this.peliculaRepository.getPeliculasByNombre(titulo);

                    //Crear pelicula si no existe
                    Pelicula pelicula;
                    //En caso de que exista, asignarselo a la funcion
                    if (peliculaOpt.isPresent()) {
                        pelicula = peliculaOpt.get();
                        System.out.println("Ya existe la película: " + titulo);
                    }else { //Asignarle los datos si no existe
                        pelicula = new Pelicula();
                        pelicula.setNombre(titulo);
                        pelicula.setAnio(anio);

                        //Partimos de la linea de autor con año, y se busca hacia arriba o abajo
                        for (int j = Math.max(i - 1, 0); j <= min; j++) {
                            contexto.append(lineas[j]).append(" ");
                        }
                        String datosPeliculas = contexto.toString();
                        //Detectar patrones
                        Matcher duracionMatcher = PATRON_DURACION.matcher(datosPeliculas);
                        Matcher formatoMatcher = PATRON_FORMATO.matcher(datosPeliculas);
                        Matcher idiomaMatcher = PATRON_IDIOMA.matcher(datosPeliculas);
                        Matcher colorMatcher = PATRON_COLOR.matcher(datosPeliculas);



                        //Asignar los atributos a la película
                        if (duracionMatcher.find()) pelicula.setDuracion(Integer.parseInt(duracionMatcher.group(1)));
                        if (formatoMatcher.find()) formatoRepository.getFormatoByNombre(formatoMatcher.group(1)).ifPresent(pelicula::setFormato);
                        if (idiomaMatcher.find()) lenguajeRepository.getLenguajesByNombre(idiomaMatcher.group(1)).ifPresent(pelicula::setLenguaje);
                        if (colorMatcher.find()) colorRepository.getColorByColor(colorMatcher.group(1)).ifPresent(pelicula::setColor);

                        //Guardar la película en la base de datos
                        peliculaRepository.save(pelicula);

                        //Completar la hora de la función

                        //Esto se puede borrar, solo es para confirmar por consola los datos
                        System.out.println("🎬 Película: " + pelicula.getNombre());
                        System.out.println("📅 Año: " + pelicula.getAnio());
                        System.out.println("⏱️ Duración: " + pelicula.getDuracion());
                        System.out.println("📽️ Formato: " + (pelicula.getFormato() != null ? pelicula.getFormato().getNombre() : "N/A"));
                        System.out.println("🗣️ Idioma: " + (pelicula.getLenguaje() != null ? pelicula.getLenguaje().getNombre() : "N/A"));
                        System.out.println("🎨 Color: " + (pelicula.getColor() != null ? pelicula.getColor().getColor() : "N/A"));
                        System.out.println("🕒 Función: " + funcion.getFechaHora());
                        System.out.println("🏛️ Sala: " + (funcion.getSala() != null ? funcion.getSala().getNombre() : "N/A"));
                        System.out.println("──────────────────────────────");

                    }
                    // Asignar película a la función, nueva o existente
                    funcion.setPelicula(pelicula);
                    // Comprobar que la sala y fechaHora están asignadas antes de comprobar si existe
                    if (funcion.getFechaHora() != null && funcion.getSala() != null) {
                        if (funcionRepository.existsFuncionByFechaHoraAndSala(funcion.getFechaHora(), funcion.getSala())) {
                            System.out.println("Ya existe la función");
                        } else {
                            funcionRepository.save(funcion);
                        }
                    } else {
                        System.out.println("No se puede guardar la función: fechaHora o sala son nulos");
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
