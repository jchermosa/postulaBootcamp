package entity;

public class Bootcamp {
    private int id, id_lenguaje, id_profesor;
    private String fecha_inicio,fecha_fin,descripcion,imagen,titulo;
    private boolean activo;

    public Bootcamp() {

    }

    public Bootcamp(int id_lenguaje, int id_profesor, String fecha_inicio, String fecha_fin, String descripcion, String imagen, String titulo, boolean activo) {
        this.id_lenguaje = id_lenguaje;
        this.id_profesor = id_profesor;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.titulo = titulo;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public int getId_lenguaje() {
        return id_lenguaje;
    }

    public void setId_lenguaje(int id_lenguaje) {
        this.id_lenguaje = id_lenguaje;
    }

    public int getId_profesor() {
        return id_profesor;
    }

    public void setId_profesor(int id_profesor) {
        this.id_profesor = id_profesor;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}

