package com.allura.literalura.repository;

import com.allura.literalura.model.Autor;
import com.allura.literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libros, Long> {

}
