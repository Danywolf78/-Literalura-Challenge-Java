package com.allura.literalura.model;

public enum Idioma {
    Ingles("en"),
    Español("es"),
    Frances("fr"),
    Portugues("pt");

    private String idiomaGutendex;
    Idioma (String idiomaGutendex){
        this.idiomaGutendex= idiomaGutendex;
    }
public static Idioma fromString(String text){
        for (Idioma idioma: Idioma.values()){
            if(idioma.idiomaGutendex.equalsIgnoreCase(text)){
                return idioma;
            }
        }
    throw new IllegalArgumentException("Idioma no encontrado: " + text);
}
}


