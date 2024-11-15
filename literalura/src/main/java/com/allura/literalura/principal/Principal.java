package com.allura.literalura.principal;

import com.allura.literalura.model.*;
import com.allura.literalura.repository.AutorRepository;
import com.allura.literalura.repository.LibrosRepository;
import com.allura.literalura.service.ConsumoAPI;
import com.allura.literalura.service.ConvierteDatos;
import jakarta.persistence.Id;
import org.yaml.snakeyaml.events.Event;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private List<DatosLibros> datosLibros=new ArrayList<>();
    private LibrosRepository librosRepository;
    private AutorRepository autorRepository;

    public Principal(LibrosRepository librosRepository, AutorRepository autorrepository) {
        this.librosRepository= librosRepository;
        this.autorRepository = autorrepository;
    }


    public void muestraElMenu() {
        var opcion= -1;
        while(opcion !=0){
            var menu= """
                    1- Buscar libro por título
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    6- Mostrar libros buscados
                    0- Salir
                    
                    """;
            System.out.println(menu);
            opcion =teclado.nextInt();
            teclado.nextLine();
            switch (opcion ){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();//de  la base de datos
                    break;
                case 3:
                    listarAutores();//de  la base de datos
                    break;
                case 4:
                    ListarAutoresVivos();//de  la base de datos
                    break;
                case 5:
                    listarLibrosPorIdioma();//de  la base de datos
                    break;
                case 6:
                    mostrarLibrosBuscados();//de  la base de datos
                    break;
                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        } }

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

            // Buscar el autor en la base de datos usando su nombre
            Autor autor = autorRepository.findByNombre(datosAutor.nombre());

            // Si no se encuentra el autor, lo creamos y lo guardamos
            if (autor == null) {
                autor = new Autor(datosAutor);  // Crear un nuevo autor con los datos del autor
                autorRepository.save(autor); // Guardar el autor en la base de datos
            }

            // Crear el libro y asociarlo al autor
            Libros libros = new Libros(datos); // Crear libro a partir de los datos
            libros.setAutor(autor); // Establecer el autor en el libro

            // Guardar el libro en la base de datos
            librosRepository.save(libros);

            System.out.println(libros); // Mostrar los datos del libro
        }
    }

    private void listarLibrosPorIdioma() {
    }

    private void ListarAutoresVivos() {
    }

    private void listarAutores() {
    }
    private void listarLibros() {
    }

    private void mostrarLibrosBuscados() {
        List<Libros> librosBuscados= new ArrayList<>();
        librosBuscados = datosLibros.stream()
                .map(d-> new Libros(d))
                .collect(Collectors.toList());
        librosBuscados.stream()
                .sorted(Comparator.comparing(Libros::getDowloads))
                .forEach(System.out::println);


    }


}

//         private void buscarLibroPorTitulo() {
//           DatosLibros datos= getDatosLibros();
//           if (datos!=null){
//           //datosLibros.add(datos);
//           Libros libros= new Libros(datos);
//           Autor autor=new Autor(datos);
//           librosRepository.save(libros);
//           //autorRepository.save(autor);
//            System.out.println(datos);
//          }
//        }



//        // Lista inicial de libros obtenida del JSON
//        List<DatosLibros> datosLibrosList = datos.datosLibros().stream()
//                .filter(Objects::nonNull) // Opcional: filtra para omitir elementos nulos
//                .collect(Collectors.toList());
//        // Convierte `DatosLibros` en `Libros` y guarda en una nueva lista
//        List<Libros> librosList = datosLibrosList.stream()
//                .map(d -> new Libros(d.Id(), d.titulo(),d.datosAutor(), d.idioma(), d.copyright(),d.dowloads()))
//                .collect(Collectors.toList());
//
//
//        // Muestra el título de cada libro
//        //datosLibrosList.forEach(libro -> System.out.println(libro.titulo()));
//
//
//        // Opcional: muestra cada libro convertido
//         librosList.forEach(System.out::println);
//        //librosList.forEach(libro -> System.out.println(libro));
//
//        //Busqueda de libros a partir de x año
//        System.out.println("Por favor indica el año a partirdel cual  deseas ver el autor");
//        var aMuerte= teclado.nextInt();
//        teclado.nextLine();
//        librosList.stream()
//                .filter(libro -> libro.getDatosAutor().stream()
//                        .anyMatch(autor -> autor.aMuerte() != null && autor.aMuerte() > aMuerte))
//                .forEach(libro -> {
//                    System.out.println("Nombre del Libro: " + libro.getTitulo());
//                    libro.getDatosAutor().forEach(autor ->
//                            System.out.println("Autor: " + autor.nombre() + ", Año de Muerte: " + autor.aMuerte())
//                    );
//                });
//
//
////        List<DatosLibros> librosFiltrados = datos.datosLibros().stream()
////                .filter(libro -> libro.datosAutor() != null)  // Filtra libros sin autores
////                .filter(libro -> libro.datosAutor().stream()   // Para cada libro, verifica la lista de autores
////                        .anyMatch(autor -> autor.aMuerte() != null && autor.aMuerte() < aMuerte)) // Compara el año de muerte
////                .collect(Collectors.toList());
////    }
//
//

//
//        List<DatosLibros> top5Libros = libros.stream()
//                .filter(Objects::nonNull) // Filtrar nulos si es necesario
//                .sorted(Comparator.comparing(DatosLibros::Dowloads).reversed()) // Ordenar por descargas en orden descendente
//                .limit(5) // Tomar los primeros 5
//                .collect(Collectors.toList());
//        top5Libros.forEach(libro ->
//                System.out.println("Título: " + libro.titulo() + ", Descargas: " + libro.Dowloads())
//        );

//        if (datos != null && datos.datosLibros() != null && !datos.datosLibros().isEmpty()) {
//            List<DatosLibros> libros = new ArrayList<>();
//
//            // Verifica si el tamaño es correcto y recorre
//            for (int i = 0; i < datos.datosLibros().size(); i++) {
//                DatosLibros libro = datos.datosLibros().get(i);
//                if (libro != null) {
//                    libros.add(libro);
//                }
//            }
////            // Mostrar los libros
//            //libros.forEach(libro -> System.out.println(libro));
        //Mostrar solo el titulo de los libros
//            System.out.println("Títulos de los libros:");
//            libros.forEach(libro -> System.out.println(libro.titulo()));
        //Convertir todas las infornaciones a una lista del tipo datos Episodios

//
//        } else {
//            System.out.println("No se encontraron libros con ese nombre.");
//        }
//    }














