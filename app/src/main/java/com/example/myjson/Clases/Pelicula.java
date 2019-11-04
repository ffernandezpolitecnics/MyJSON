package com.example.myjson.Clases;

import java.util.List;

/**
 *
 */
public class Pelicula {
    private String titulo;
    private String director;
    private int anyoEstreno;
    private List<String> temas;


    public Pelicula(String titulo, String director, int anyoEstreno, List<String> temas) {
        this.titulo = titulo;
        this.director = director;
        this.anyoEstreno = anyoEstreno;
        this.temas = temas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnyoEstreno() {
        return anyoEstreno;
    }

    public void setAnyoEstreno(int anyoEstreno) {
        this.anyoEstreno = anyoEstreno;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }
}
