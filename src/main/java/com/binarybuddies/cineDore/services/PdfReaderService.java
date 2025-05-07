package com.binarybuddies.cineDore.services;

import com.binarybuddies.cineDore.models.*;
import com.binarybuddies.cineDore.repositories.*;
import jakarta.transaction.Transactional;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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

    /**
     * Parsea las páginas donde están las películas
     *
     * @param filePath La ruta donde está el pdf descargado
     * @return Devuelve un string con toda la información de esas páginas del pdf
     * @throws IOException si da error mientras procesa el pdf
     */
    public String procesarPdfpeliculas(String filePath) throws IOException {

         try (PDDocument document = Loader.loadPDF(new File(filePath))) {
             PDFTextStripper stripper = new PDFTextStripper();

             // Establecer el rango de páginas (1 es la primera página, 6 es la última página que queremos leer)
             stripper.setStartPage(1);  // Primera página
             stripper.setEndPage(6);    // Última página a leer

             return stripper.getText(document);

         }catch (FileNotFoundException e) {
             System.out.println("No se encontro el archivo en la ruta");
             return null;
         }
    }

    /**
     * Extraer datos del pdf y pasarlos a la base de datos.
     * Comprueba las fechas, crea una función por fecha y hora.
     * Crea películas, comprueba repetidas.
     * Asocia función con cada película.
     *
     * @param texto Solicitud del texto del pdf parseado
     */
    @Transactional
    public void extraerDatosPeliculasFunciones(String texto) {
        Calendar fecha = extraerFechaGlobal(texto);
        Matcher bloqueDiaMatcher = PATRON_BLOQUE_DIA.matcher(texto);

        while (bloqueDiaMatcher.find()) {
            int diaNumero = Integer.parseInt(bloqueDiaMatcher.group(2));
            fecha.set(Calendar.DAY_OF_MONTH, diaNumero);
            String bloque = bloqueDiaMatcher.group(0);
            procesarBloqueFunciones(bloque, fecha);
        }
    }

    /**
     * Extraer el mes y el año
     *
     * @param texto Solicitud del texto del pdf parseado
     * @return fecha de la función,
     */
    private Calendar extraerFechaGlobal(String texto) {
        Calendar fecha = Calendar.getInstance();
        Matcher anioMatcher = PATRON_ANIO.matcher(texto);
        if (anioMatcher.find()) {
            fecha.set(Calendar.YEAR, Integer.parseInt(anioMatcher.group()));
        }
        Matcher mesMatcher = PATRON_MES.matcher(texto);
        if (mesMatcher.find()) {
            int mes = obtenerNumeroMes(mesMatcher.group(1));
            fecha.set(Calendar.MONTH, mes - 1);
        }
        return fecha;
    }

    /**
     * Procesar las líneas dentro de cada bloque por fecha.
     * Utiliza el patrón del autor + fecha para buscar líneas anteriores y posteriores
     *
     * @param bloque el bloque de texto
     * @param fecha la fecha de la función
     */
    private void procesarBloqueFunciones(String bloque, Calendar fecha) {
        String[] lineas = bloque.split("\\R");

        for (int i = 1; i < lineas.length; i++) {
            String linea = lineas[i].trim();
            Matcher autorMatcher = PATRON_AUTOR_ANIO.matcher(linea);

            if (autorMatcher.find()) {
                procesarFuncion(lineas, i, autorMatcher, fecha);
            }
        }
    }

    /**
     * Crea las funciones, les asigna sus datos correspondientes y lo guarda.
     *
     * @param lineas las líneas dentro de cada mini division
     * @param autorMatcher busca el autor y año para saber donde empezar
     * @param fecha la fecha de la función
     */
    private void procesarFuncion(String[] lineas, int i, Matcher autorMatcher, Calendar fecha) {
        String titulo = lineas[i - 1].trim();
        int anio = Integer.parseInt(autorMatcher.group(2));
        Funcion funcion = new Funcion();

        String datosContexto = construirContexto(lineas, i);
        LocalDateTime fechaHora = extraerFechaHora(datosContexto, fecha);
        funcion.setFechaHora(fechaHora);

        Optional<Sala> salaOpt = extraerSala(datosContexto);
        salaOpt.ifPresent(funcion::setSala);

        Pelicula pelicula = obtenerOPersistirPelicula(titulo, anio, lineas, i);
        funcion.setPelicula(pelicula);

        guardarFuncionSiNoExiste(funcion);
    }

    /**
     * Extrae la fecha y la hora de cada función
     *
     * @param contexto el bloque de texto dentro del día
     * @param fecha la fecha de la función
     * @return hora y minuto para la función
     */
    private LocalDateTime extraerFechaHora(String contexto, Calendar fecha) {
        Matcher horaMatcher = PATRON_HORA.matcher(contexto);
        if (horaMatcher.find()) {
            String[] partesHora = horaMatcher.group(1).split(":");
            fecha.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partesHora[0]));
            fecha.set(Calendar.MINUTE, Integer.parseInt(partesHora[1]));
            return fecha.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }

    /**
     * Comprueba si existe la sala
     *
     * @param contexto la mini división por función
     * @return sala si existe
     */
    private Optional<Sala> extraerSala(String contexto) {
        Matcher salaMatcher = PATRON_SALA.matcher(contexto);
        if (salaMatcher.find()) {
            return salaRepository.getSalaByNombre("Sala " + salaMatcher.group(1));
        }
        return Optional.empty();
    }

    /**
     * Crea una película con sus datos si no existe en la base de datos
     * Le asigna el título y el año
     *
     * @param titulo título de la película
     * @param anio año de la película
     * @param lineas array para el contexto de la función
     * @param i iterador
     *
     * @return película
     */
    private Pelicula obtenerOPersistirPelicula(String titulo, int anio, String[] lineas, int i) {
        Optional<Pelicula> peliculaOpt = peliculaRepository.getPeliculasByNombre(titulo);

        if (peliculaOpt.isPresent()) {
            System.out.println("Ya existe la película: " + titulo);
            return peliculaOpt.get();
        }

        Pelicula pelicula = new Pelicula();
        pelicula.setNombre(titulo);
        pelicula.setAnio(anio);

        String contexto = construirContexto(lineas, i);
        asignarDatosPeliculasDesdeTexto(pelicula, contexto);
        peliculaRepository.save(pelicula);

        return pelicula;
    }

    /**
     * A la película guardada anteriormente, le asigna los datos restantes
     * Busca si esos respectivos datos existen en la base de datos para poder asignarlos.
     *
     * @param pelicula la película creada anteriormente
     * @param datos contexto de la función
     */
    private void asignarDatosPeliculasDesdeTexto(Pelicula pelicula, String datos) {
        Matcher duracionMatcher = PATRON_DURACION.matcher(datos);
        Matcher formatoMatcher = PATRON_FORMATO.matcher(datos);
        Matcher idiomaMatcher = PATRON_IDIOMA.matcher(datos);
        Matcher colorMatcher = PATRON_COLOR.matcher(datos);

        if (duracionMatcher.find()) pelicula.setDuracion(Integer.parseInt(duracionMatcher.group(1)));
        if (formatoMatcher.find()) formatoRepository.getFormatoByNombre(formatoMatcher.group(1)).ifPresent(pelicula::setFormato);
        if (idiomaMatcher.find()) lenguajeRepository.getLenguajesByNombre(idiomaMatcher.group(1)).ifPresent(pelicula::setLenguaje);
        if (colorMatcher.find()) colorRepository.getColorByColor(colorMatcher.group(1)).ifPresent(pelicula::setColor);
    }

    /**
     * Guarda la función si no existe
     *
     * @param funcion la función creada anteriormente
     */
    private void guardarFuncionSiNoExiste(Funcion funcion) {
        if (funcion.getFechaHora() != null && funcion.getSala() != null) {
            if (!funcionRepository.existsFuncionByFechaHoraAndSala(funcion.getFechaHora(), funcion.getSala())) {
                funcionRepository.save(funcion);
            } else {
                System.out.println("Ya existe la función");
            }
        } else {
            System.out.println("No se puede guardar la función: fechaHora o sala son nulos");
        }
    }

    /**
     * Construye el contexto alrededor del patrón de autor + año
     *
     * @param lineas donde está el contexto de cada función
     * @param i iterador para recorrer
     */
    private String construirContexto(String[] lineas, int i) {
        StringBuilder contexto = new StringBuilder();
        int min = Math.min(i + 2, lineas.length - 1);
        for (int j = Math.max(i - 2, 0); j <= min; j++) {
            contexto.append(lineas[j]).append(" ");
        }
        return contexto.toString();
    }

    /**
     * Obtiene el mes en número del pdf para poder asignarlo bien en el LocalDateTime
     *
     * @param mes el bloque de texto
     */
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
