package com.fuentesbuenosvinosguillermo.mariobrosbinding.Recyclerview;

import androidx.recyclerview.widget.RecyclerView;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.PersonajesCardviewBinding;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.InfoPersonajes;

/**
 * Clase ViewHolderRecycler que extiende RecyclerView.ViewHolder.
 * Esta clase es necesaria para que un RecyclerView funcione correctamente, ya que es responsable de contener
 * y mostrar los datos de cada elemento en la lista.
 *
 * Un ViewHolder es un patrón de diseño utilizado para representar cada ítem en el RecyclerView y mostrar
 * sus propiedades en pantalla. Esta clase se utilizará en el Adapter para manejar la lógica de presentación
 * del RecyclerView.
 */
public class ViewHolderRecycler extends RecyclerView.ViewHolder {

    /**
     * Binding que permite acceder a las vistas definidas en el layout personajes_cardview.xml.
     * Facilita la manipulación de las vistas sin necesidad de hacer llamadas repetitivas a findViewById.
     */
    private final PersonajesCardviewBinding binding;

    /**
     * Constructor de la clase ViewHolderRecycler.
     *
     * @param binding El objeto de tipo PersonajesCardviewBinding que conecta las vistas del layout
     *                personajes_cardview.xml con los datos que se mostrarán en cada ítem del RecyclerView.
     */
    public ViewHolderRecycler(PersonajesCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Método bind que establece los datos del objeto InfoPersonajes en las vistas del layout.
     * Este método recibe un objeto InfoPersonajes, y a través de binding, asigna los valores
     * correspondientes a cada vista en el layout personajes_cardview.xml.
     *
     * @param personaje El objeto InfoPersonajes que contiene la información del personaje,
     *                  incluyendo nombre, imagen y otras propiedades.
     */
    public void bind(InfoPersonajes personaje) {
        // Establece la imagen del personaje
        binding.image.setImageResource(personaje.getImagen());

        // Establece el nombre del personaje
        binding.nombrepersonaje.setText(personaje.getNombre());

        // Ejecuta cualquier binding pendiente
        binding.executePendingBindings();
    }
}
