package com.roshka.proyectofinal.entity;

public class Lenguaje {
    private int id;
    private String nombre_lenguaje;

    public Lenguaje() {

    }

    public Lenguaje(int id, String nombre_lenguaje) {
        this.id = id;
        this.nombre_lenguaje = nombre_lenguaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre_lenguaje() {
        return nombre_lenguaje;
    }

    public void setNombre_lenguaje(String nombre_lenguaje) {
        this.nombre_lenguaje = nombre_lenguaje;
    }
}
