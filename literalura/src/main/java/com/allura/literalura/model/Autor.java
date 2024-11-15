package com.allura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name="autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer aNacimiento;
    private Integer aMuerte;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@OneToMany(mappedBy = "autor")
    private List<Libros> libros;


    public Autor() {}

    public List<Libros> getLibros(List<Libros> libros) {
        libros.forEach(l -> l.setAutor(this));
        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.aNacimiento = datosAutor.aNacimiento();
        this.aMuerte = datosAutor.aMuerte();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getaNacimiento() {
        return aNacimiento;
    }

    public void setaNacimiento(Integer aNacimiento) {
        this.aNacimiento = aNacimiento;
    }

    public Integer getaMuerte() {
        return aMuerte;
    }

    public void setaMuerte(Integer aMuerte) {
        this.aMuerte = aMuerte;
    }

    @Override
    public String toString() {
        return "\n------------ Autor ------------\n" +
                      "\n Id=" + id +
                      "\n Nombre='" + nombre + '\'' +
                      "\n Año de Nacimiento=" + aNacimiento +
                      "\n Año de Fallecimiento=" + aMuerte +
                "\n-----------------------------------\n";
    }

}
