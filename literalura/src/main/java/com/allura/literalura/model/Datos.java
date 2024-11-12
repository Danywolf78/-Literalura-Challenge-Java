package com.allura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos(
        @JsonAlias("count")Integer totalLibros,
        @JsonAlias("results") List<DatosLibros> datosLibros
)
{
}
