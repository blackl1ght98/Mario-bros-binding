package com.fuentesbuenosvinosguillermo.mariobrosbinding;

public class InfoPersonajes {
    private String nombre;
    private String descripcion;
    private String habilidades;
    private String caracteristicas;
    private int imagen;

    public InfoPersonajes() {
        // Constructor vacío
    }

    public InfoPersonajes(String nombre, String descripcion, String habilidades, String caracteristicas, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
        this.caracteristicas = caracteristicas;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
