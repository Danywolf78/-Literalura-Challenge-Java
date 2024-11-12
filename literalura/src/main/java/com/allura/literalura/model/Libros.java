package com.allura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class Libros {
    private Long Id;
    private String titulo;
    private Boolean copyright;
    private List<DatosAutor> datosAutor;
    private List<String> idioma;
    private Double dowloads ;

    public Libros(DatosLibros datosLibros) {
        this.Id=datosLibros.Id();
        this.titulo=datosLibros.titulo();
        this.datosAutor=datosLibros.datosAutor();
        this.idioma=datosLibros.idioma();
        this.copyright=datosLibros.copyright();
        this.dowloads=datosLibros.dowloads();
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

    public List<DatosAutor> getDatosAutor() {
        return datosAutor;
    }

    public void setDatosAutor(List<DatosAutor> datosAutor) {
        this.datosAutor = datosAutor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Double getDowloads() {
        return dowloads;
    }

    public void setDowloads(Double dowloads) {
        this.dowloads = dowloads;
    }

    @Override
    public String toString() {
        return
               // "Id=" + Id +
                ", idioma=" + idioma +
                ", titulo='" + titulo + '\'' +
                ", copyright=" + copyright +
                ", datosAutor=" + datosAutor +

                ", dowloads=" + dowloads
                ;
    }
}

//
//    public Long getId() {
//        return Id;
//    }
//
//    public void setId(Long id) {
//        Id = id;
//    }
//
//    public String getTitulo() {
//        return titulo;
//    }
//
//    public void setTitulo(String titulo) {
//        this.titulo = titulo;
//    }
//
//    public Boolean getCopyright() {
//        return copyright;
//    }
//
//    public void setCopyright(Boolean copyright) {
//        this.copyright = copyright;
//    }
//
//    public List<DatosAutor> getDatosAutor() {
//        return datosAutor;
//    }
//
//    public void setDatosAutor(List<DatosAutor> datosAutor) {
//        this.datosAutor = datosAutor;
//    }
//
//    public List<String> getIdioma() {
//        return idioma;
//    }
//
//    public void setIdioma(List<String> idioma) {
//        this.idioma = idioma;
//    }
//
//    public Double getDowloads() {
//        return dowloads;
//    }
//
//    public void setDowloads(Integer dowloads) {
//        dowloads = dowloads;
//    }
//
//    @Override
//    public String toString() {
//        return
//                "Id=" + Id +
//                ", titulo='" + titulo + '\'' +
//                ", copyright=" + copyright +
//                ", datosAutor=" + datosAutor +
//                ", idioma=" + idioma +
//                ", Dowloads=" + dowloads
//                ;
//    }
//}
//
//
//