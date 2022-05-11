package com.roshka.proyectofinal;

//Creacion del objeto Postulante
public class Postulante {

    private int id,nroCedula;
    private String nombre,apellido,telefono,direccion,correo;
    private boolean   expLaboral,estudioUniversitario,notebook,bootcampId,aceptado;

    //Los parametros que reciban los metodos get estaran en ingles con camelCase para evitar confusiones


    public Postulante() {
    }

    public Postulante(int nroCedula, String nombre, String apellido, String telefono, String direccion, String correo, boolean expLaboral, boolean estudioUniversitario, boolean notebook, boolean bootcampId, boolean aceptado) {
        this.nroCedula = nroCedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.correo = correo;
        this.expLaboral = expLaboral;
        this.estudioUniversitario = estudioUniversitario;
        this.notebook = notebook;
        this.bootcampId = bootcampId;
        this.aceptado = aceptado;
    }
    public int getId() {
        return id;
    }
    public int getNro_cedula() {
        return nroCedula;
    }
    public void setNro_cedula(int card_id) {
        this.nroCedula = card_id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setName(String name) {
        this.nombre = name;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String lastName) {
        this.apellido = lastName;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telephone) {
        this.telefono = telephone;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String addres) {
        this.direccion = addres;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String email) {
        this.correo = email;
    }
    public boolean getExpLaboral(){
        return expLaboral;
    }
    public void setExpLaboral(boolean laboralExperience){
        this.expLaboral = laboralExperience;
    }
    public boolean getEstudioUniversitario(){
        return estudioUniversitario;
    }
    public void setEstudioUniversitario(boolean university){
        this.estudioUniversitario = university;
    }
    public boolean getNotebook(){
        return notebook;
    }
    public void setNotebook(boolean notebook){
        this.notebook = notebook;
    }
    public boolean getBootcampId(){
        return bootcampId;
    }
    public void setBootcampId(boolean bootcampId){
        this.bootcampId = bootcampId;
    }

}
