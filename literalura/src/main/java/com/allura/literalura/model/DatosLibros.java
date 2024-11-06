package com.allura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(@JsonAlias("id") Long Id,
                          @JsonAlias("title")String titulo,
                          @JsonAlias("copyright")String copyrigth,
                          @JsonAlias("authors")List<DatosAutor> datosAutores,
                          @JsonAlias("download_count")Integer Dowloads
                          ) {
}
