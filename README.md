# Literalura-Challenge-Java
Proyecto de una biblioteca de libros desarrollado en Java con Spring Boot que permite almacenar y gestionar
información de libros a través de una API y realizar búsquedas en una base de datos PostgreSQL.

## Características

- **Consulta de libros**: Busca libros por título, autores, idioma, número de descargas tambien buscar autores vivos en un año específico,.
- **Almacenamiento**: Guarda libros y autores en PostgreSQL.
- **Estadísticas**: Calcula estadísticas sobre el número de descargas de los libros.
- **Consulta de API**: Obtiene datos de libros de la API de Gutendex y los guarda en una base de datos.

## Tecnologías

- **Java 17 o superior**
- **Spring Boot**: Framework principal.
- **Gradle**: Gestión de dependencias.
- **JPA (Java Persistence API)** y **Hibernate**: Gestión de entidades y relaciones en la base de datos.
- **PostgreSQL**: Base de datos para libros y autores.
- **Jackson**: Deserialización de datos JSON.
- **Gutendex API**: Fuente de información de libros.

## Instalación y Configuración

1. **Clona el repositorio**:
   ```
    https://github.com/Danywolf78/-Literalura-Challenge-Java
   ```
2. **Configura la base de datos**:
   - Crea una base de datos en PostgreSQL (ej. mdleo_literalura).
   - Configura `application.properties` con tus Datos.     
3. **Ejecuta la aplicación**:
   
   - `mvn spring-boot:run`
   

## Uso

Al iniciar la aplicación, verás un menú en la consola con las siguientes opciones:

1. **Buscar Libros**: Busca libros en la API de Gutendex y los guarda en la base de datos.
2. **Listar Libros**: Muestra todos los libros almacenados.
3. **Listar Autores**: Muestra todos los autores y sus libros en la base de datos.
4. **Listar Autores vivos segun año**: Encuentra autores vivos por año.
5. **Listar Libros por idioma**: Filtra libros por idioma.
6. **Busca Libros buscados**:libros ordenados segun numeros de descargas.

## Documentos Complementarios

- Spring Boot Documentation: https://docs.spring.io/spring-boot/index.html
- JPA Query Methods: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
- API Gutendex: https://gutendex.com
- Postman: https://www.postman.com/downloads/

# Cómo Contribuir
  Si deseas contribuir a este proyecto, sigue estos pasos:

- Haz un fork del repositorio.
- Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
- Realiza tus cambios y haz commit (git commit -am 'Agrega nueva funcionalidad').
- Sube tus cambios (git push origin feature/nueva-funcionalidad).
- Abre un Pull Request.

  # Autor
  - Daniel Lopez  
  - [@Danywolf78](https://github.com/Danywolf78)





