package com.allura.literalura.repository;

import com.allura.literalura.model.Autor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
}
