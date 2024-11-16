package com.allura.literalura.repository;

import com.allura.literalura.model.Autor;
import com.allura.literalura.model.Idioma;
import com.allura.literalura.model.Libros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface LibrosRepository extends JpaRepository<Libros, Long> {
    @Query("SELECT l FROM Libros l JOIN l.autor a")
    List<Libros> listarLibros();
    @Query("SELECT l FROM Libros l WHERE l.idioma = :idioma")
    List<Libros> buscarLibrosPorIdioma(@Param("idioma") Idioma idioma);

}