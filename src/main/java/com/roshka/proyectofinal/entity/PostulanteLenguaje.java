package com.roshka.proyectofinal.entity;

public class PostulanteLenguaje {

    private int idPostulante,idLenguaje,id;

    public PostulanteLenguaje() {
    }

    public PostulanteLenguaje(int idPostulante) {
        this.idPostulante = idPostulante;
        this.idLenguaje = idPostulante;

    }
    public int getId(){
        return id;
    }
    public int getIdPostulante(){
        return idPostulante;
    }
    public void setIdPostulante(int idPostulante) {
        this.idPostulante = idPostulante;
    }

    public int getIdLenguaje() {
        return idLenguaje;
    }

    public void setIdLenguaje(int idLenguaje) {
        this.idLenguaje = idLenguaje;
    }
}
