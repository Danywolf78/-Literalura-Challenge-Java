package com.allura.literalura.model;

public enum Idioma {
    Ingles("en"),
    Español("es"),
    Frances("fr"),
    Portugues("pt"),
    Italiano("it");
    private String idiomaGutendex;
    Idioma (String idiomaGutendex){
        this.idiomaGutendex= idiomaGutendex;
    }
    public static Idioma fromString(String text) {
        try {
            for (Idioma idioma : Idioma.values()) {
                if (idioma.idiomaGutendex.equalsIgnoreCase(text)) {
                    return idioma; // Devuelve el idioma si coincide
                }
            }
            // Si no se encuentra, lanza manualmente una excepción
            throw new IllegalArgumentException("Idioma no encontrado: " + text);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage()); // Imprime el error
            return Idioma.Ingles; // Retorna un idioma por defecto
        }
    }

}


