package com.allura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
    private String nombre;
    private Integer aNacimiento;
    private Integer aMuerte;


    public Autor(String nombre, Integer anoNacimiento, Integer anoMuerte) {
        this.nombre = nombre;
        this.aNacimiento = anoNacimiento;
        this.aMuerte = anoMuerte;
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
}
