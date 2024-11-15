package com.allura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name="libros")

public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    //@Column(unique=true)
    private String titulo;
    private Boolean copyright;
    @ManyToOne
    @JoinColumn(name="autor_id")
    private Autor autor;
   @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Double dowloads ;

    public Libros() {}




    public Libros(DatosLibros datosLibros) {
        this.Id=datosLibros.Id();
        this.titulo=datosLibros.titulo();
        this.idioma=Idioma.fromString(datosLibros.idioma().get(0).trim());
        this.copyright=datosLibros.copyright();
        this.dowloads=datosLibros.dowloads();

    }


    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public Double getDowloads() {
        return dowloads;
    }

    public void setDowloads(Double dowloads) {
        this.dowloads = dowloads;
    }

    @Override
    public String toString() {
        return "\n------------ Libro ------------\n" +
                       //"\n Id API=" + Id +
                       "\n Titulo='" + titulo + '\'' +
                       "\n Idioma=" + idioma +
                       "\n Copyright=" + copyright +
                       "\n Datos Autor=" + autor +
                       "\n Dowloads=" + dowloads +
                "\n-----------------------------------\n";

    }
}

