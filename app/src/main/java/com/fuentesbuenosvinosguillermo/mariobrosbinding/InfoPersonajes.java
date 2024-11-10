package com.fuentesbuenosvinosguillermo.mariobrosbinding;

/**
 * Clase que representa la información de un personaje en la aplicación.
 * Esta clase se utiliza como modelo de datos para almacenar las características de un personaje
 * y se integra con un RecyclerView para mostrar la información de forma estructurada.
 * <p>
 * Contiene atributos como nombre, descripción, habilidades, características e imagen.
 * Proporciona los métodos necesarios (getters y setters) para acceder y modificar estos atributos.
 */
public class InfoPersonajes {
    private String nombre;
    private String descripcion;
    private String habilidades;
    private String caracteristicas;
    private int imagen;

    /**
     * Constructor vacío para InfoPersonajes.
     * Se utiliza cuando se requiere crear un objeto sin definir sus atributos iniciales.
     */
    public InfoPersonajes() {
        // Constructor vacío
    }

    /**
     * Constructor completo para InfoPersonajes.
     *
     * @param nombre          El nombre del personaje.
     * @param descripcion     Una breve descripción del personaje.
     * @param habilidades     Las habilidades especiales del personaje.
     * @param caracteristicas Las características adicionales del personaje.
     * @param imagen          El recurso de imagen asociado al personaje (ID de recurso drawable).
     */
    public InfoPersonajes(String nombre, String descripcion, String habilidades, String caracteristicas, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
        this.caracteristicas = caracteristicas;
        this.imagen = imagen;
    }

    /**
     * Obtiene el recurso de imagen del personaje.
     *
     * @return El ID de recurso de la imagen del personaje.
     */
    public int getImagen() {
        return imagen;
    }

    /**
     * Establece el recurso de imagen del personaje.
     *
     * @param imagen El ID de recurso de la imagen del personaje.
     */
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el nombre del personaje.
     *
     * @return El nombre del personaje.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del personaje.
     *
     * @param nombre El nombre del personaje.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del personaje.
     *
     * @return La descripción del personaje.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del personaje.
     *
     * @param descripcion La descripción del personaje.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene las habilidades del personaje.
     *
     * @return Las habilidades del personaje.
     */
    public String getHabilidades() {
        return habilidades;
    }

    /**
     * Establece las habilidades del personaje.
     *
     * @param habilidades Las habilidades del personaje.
     */
    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    /**
     * Obtiene las características del personaje.
     *
     * @return Las características del personaje.
     */
    public String getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * Establece las características del personaje.
     *
     * @param caracteristicas Las características adicionales del personaje.
     */
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}