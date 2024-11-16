package com.allura.literalura.principal;

import com.allura.literalura.model.*;
import com.allura.literalura.repository.AutorRepository;
import com.allura.literalura.repository.LibrosRepository;
import com.allura.literalura.service.ConsumoAPI;
import com.allura.literalura.service.ConvierteDatos;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.events.Event;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros=new ArrayList<>();
    @Autowired
    private LibrosRepository librosRepository;
    private AutorRepository autorRepository;

    public Principal(LibrosRepository librosRepository, AutorRepository autorrepository) {
        this.librosRepository= librosRepository;
        this.autorRepository = autorrepository;
    }


    public void muestraElMenu() {
            var opcion = -1; // Opción inicializada
            while (opcion != 0) {

                var menu = """
                1- Buscar libro por título
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                6- Mostrar libros buscados
                0- Salir
                
                """;
                System.out.println(menu);

                // Validar entrada del usuario
                if (teclado.hasNextInt()) {
                    opcion = teclado.nextInt();
                    teclado.nextLine();

                    // Procesar la opción ingresada
                    switch (opcion) {
                        case 1 -> buscarLibroPorTitulo();
                        case 2 -> listarLibros();
                        case 3 -> listarAutores();
                        case 4 -> ListarAutoresVivos();
                        case 5 -> listarLibrosPorIdioma();
                        case 6 -> mostrarLibrosBuscados();
                        case 0 -> System.out.println("Cerrando la aplicación...");
                        default -> System.out.println("Opción inválida. Intenta nuevamente.");
                    }
                } else {
                    System.out.println("❌ Entrada inválida. Por favor, ingresa un número.");
                    teclado.nextLine();
                }
            }
        }

        private DatosLibros getDatosLibros(){
            System.out.println("Por favor escribe el nombre del libro que deseas buscar");
            var nombreLibro = teclado.nextLine();
            var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
            //System.out.println(json);
            Datos datos = conversor.obtenerDatos(json, Datos.class); // Cambiado a Datos.class para manejar la respuesta completa
            if (datos != null && !datos.datosLibros().isEmpty()) {
                return datos.datosLibros().get(0); // Retorna el primer libro encontrado
            } else {
                System.out.println("No se encontraron resultados.");
                return null;
            }

        }

    private void buscarLibroPorTitulo() {
        DatosLibros datos = getDatosLibros(); // Obtener los datos del libro
        if (datos != null && !datos.datosAutor().isEmpty()) {
            // Obtener el primer autor de la lista de autores
            DatosAutor datosAutor = datos.datosAutor().get(0);

            // Buscar el autor en la base de datos
            Autor autor = autorRepository.findByNombre(datosAutor.nombre());

            // Si no se encuentra el autor, lo creamos y lo guardamos
            if (autor == null) {
                autor = new Autor(datosAutor);  // Crear un nuevo autor
                autorRepository.save(autor); // Guardar el autor
            }

            // Crear el libro y asocia al autor
            Libros libros = new Libros(datos);
            libros.setAutor(autor);

            // Guardar el libro en la base de datos
            librosRepository.save(libros);

            System.out.println(libros); // Mostrar los datos del libro
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                --------------------------------
                 📘 LISTAR LIBROS POR IDIOMA 📘
                --------------------------------
                """);
        var menu = """
                   
                    Seleccione el numero correespondiente al idioma del libro que desea encontrar:
                    ---------------------------------------------------
                    1 - Español
                    2 - Francés
                    3 - Inglés
                    4 - Portugués
                    5 - Italiano
                    
                    """;
        System.out.println(menu);

        try {
            var opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    buscarLibrosPorIdioma("es");
                    break;
                case 2:
                    buscarLibrosPorIdioma("fr");
                    break;
                case 3:
                    buscarLibrosPorIdioma("en");
                    break;
                case 4:
                    buscarLibrosPorIdioma("pt");
                    break;
                case 5:
                   buscarLibrosPorIdioma("it");
                break;
                default:
                    System.out.println("Opción inválida!");
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida: " + e.getMessage());
        }
    }
    private void buscarLibrosPorIdioma(String idioma) {

        try {
            Idioma idiomaEnum =  Idioma.fromString(idioma);

            // Usamos el repositorio para buscar los libros por idioma
            List<Libros> libros = librosRepository.buscarLibrosPorIdioma(idiomaEnum);
            if (libros.isEmpty()) {
                System.out.println("No hay libros registrados en ese idioma");
            } else {
                System.out.println();
                libros.forEach(l -> System.out.println(
                        "----------- LIBRO \uD83D\uDCD9  --------------" +
                                "\nTítulo: " + l.getTitulo() +
                                "\nAutor: " + l.getAutor().getNombre() +
                                "\nIdioma: " + l.getIdioma() +
                                "\nNúmero de descargas: " + l.getDowloads() +
                                "\n----------------------------------------\n"
                ));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Introduce un idioma válido en el formato especificado.");
        }
    }


    private void ListarAutoresVivos() {
        System.out.println("""
                    -----------------------------
                      📒 LISTAR AUTORES VIVOS 📒
                    -----------------------------
                     """);
        System.out.println("Introduzca un año para mostrar autor(es) vivos antes del ese año dado:");
        try {
            var fecha = Integer.valueOf(teclado.nextLine());
            List<Autor> autores = autorRepository.buscarAutoresVivos(fecha);
            if (!autores.isEmpty()) {
                System.out.println();
                autores.forEach(a -> System.out.println(
                        "Autor: " + a.getNombre() +
                                "\nFecha de Nacimiento: " + a.getaNacimiento() +
                                "\nFecha de Fallecimiento: " + a.getaMuerte() +
                                "\nLibros: " + a.getLibros().stream()
                                .map(l -> l.getTitulo()).collect(Collectors.toList()) + "\n"
                ));
            } else {
                System.out.println("No hay autor(es) vivos antes del año registrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ingresa un año válido " + e.getMessage());
        }
    }

    private void listarAutores() {
        System.out.println("""
                  LISTAR AUTORES REGISTRADOS 📕
                                      """);
        List<Autor> autores = autorRepository.findAll();
        System.out.println();
        autores.forEach(l -> System.out.println(
                "Autor: " + l.getNombre() +
                        "\nFecha de Nacimiento: " + l.getaNacimiento() +
                        "\nFecha de Fallecimiento: " + l.getaMuerte() +
                        "\nLibros: " + l.getLibros().stream()
                        .map(t -> t.getTitulo()).collect(Collectors.toList()) + "\n"
        ));
    }

    private void listarLibros() {
        System.out.println("""
                  📕 LISTAR LIBROS REGISTRADOS 📕
                                      """);
        List<Libros> libros = librosRepository.listarLibros();
        libros.forEach(l -> System.out.println(
                "-------------- 📕 LIBRO  -----------------" +
                        "\nTítulo: " + l.getTitulo() +
                        "\nAutor: " + l.getAutor().getNombre() +
                        "\nIdioma: " + l.getIdioma() +
                        "\nNúmero de descargas: " + l.getDowloads() +
                        "\n----------------------------------------\n"
        ));
    }


    private void mostrarLibrosBuscados() {

            System.out.println("Libros ordenados por descargas:");
            librosRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Libros::getDowloads).reversed()) // Orden por descargas.
                    .forEach(System.out::println); // Mostrar libros.
        }








    }





