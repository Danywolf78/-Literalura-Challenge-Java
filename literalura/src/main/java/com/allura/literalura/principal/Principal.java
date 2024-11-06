package com.allura.literalura.principal;

import com.allura.literalura.model.Datos;
import com.allura.literalura.model.DatosLibros;
import com.allura.literalura.service.ConsumoAPI;
import com.allura.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado=new Scanner(System.in);
    private ConsumoAPI consumoApi= new ConsumoAPI();
    private final String URL_BASE="https://gutendex.com/books/?search=";
    private ConvierteDatos conversor= new ConvierteDatos();

    public void muestraElMenu(){
        System.out.println("por favor escribe el nombre del libro que desea buscar");
        //Busca los datos generales de la serie
        var nombreLibro= teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+nombreLibro.replace(" ","+"));
        var datos=conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);
        //Busca los datos de los libros
        List<DatosLibros> libros= new ArrayList<>();
        for (int i = 0; i <= datos.total(); i++) {
            json = consumoApi.obtenerDatos(URL_BASE+nombreLibro.replace(" ","+")+i);
            var datosLibros =conversor.obtenerDatos(json, DatosLibros.class);
            libros.add(datosLibros);
        }
        libros.forEach(System.out::println);



    }
}
