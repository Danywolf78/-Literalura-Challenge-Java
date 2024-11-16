package com.allura.literalura.repository;

import com.allura.literalura.model.Autor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findByNombre(String nombre);
    @Query("SELECT a FROM Autor a WHERE aMuerte < :fecha")
    List<Autor> buscarAutoresVivos(@Param("fecha") Integer fecha);

}
