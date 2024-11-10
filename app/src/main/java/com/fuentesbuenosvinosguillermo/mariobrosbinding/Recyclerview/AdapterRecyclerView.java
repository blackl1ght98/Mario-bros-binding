package com.fuentesbuenosvinosguillermo.mariobrosbinding.Recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fuentesbuenosvinosguillermo.mariobrosbinding.InfoPersonajes;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.MainActivity;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.PersonajesCardviewBinding;

import java.util.ArrayList;

/**
 * AdapterRecyclerView es la clase que gestiona la lógica de visualización y eventos de un RecyclerView
 * para mostrar una lista de personajes. Este Adapter requiere de:
 * 1. La fuente de datos (lista de personajes) que mostrará el RecyclerView.
 * 2. El ViewHolder, que es el contenedor visual para cada ítem.
 * 3. La lógica de interacción y eventos (clics) en los elementos del RecyclerView.
 * <p>
 * Esta clase extiende RecyclerView.Adapter y gestiona la creación, vinculación y tamaño de los elementos
 * en el RecyclerView, así como los eventos de clic.
 */
public class AdapterRecyclerView extends RecyclerView.Adapter<ViewHolderRecycler> {

    /**
     * Fuente de datos: lista de objetos InfoPersonajes
     */
    private final ArrayList<InfoPersonajes> personajes;

    /**
     * Contexto de la actividad que utiliza este Adapter
     */
    private final Context context;

    /**
     * Constructor de AdapterRecyclerView.
     *
     * @param personajes Lista de personajes que será la fuente de datos para el RecyclerView.
     * @param context    Contexto de la actividad que maneja este Adapter.
     */
    public AdapterRecyclerView(ArrayList<InfoPersonajes> personajes, Context context) {
        this.personajes = personajes;
        this.context = context;
    }

    /**
     * Método onCreateViewHolder: crea y devuelve el ViewHolder para un ítem.
     * <p>
     * Este método infla el layout personajes_cardview.xml usando data binding, lo que permite acceder
     * directamente a las vistas en el layout sin hacer múltiples llamadas a findViewById.
     *
     * @param parent   ViewGroup en el que se añadirá la nueva vista.
     * @param viewType Tipo de vista (en este caso, solo uno).
     * @return Una nueva instancia de ViewHolderRecycler con el layout inflado.
     */
    @NonNull
    @Override
    public ViewHolderRecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonajesCardviewBinding binding = PersonajesCardviewBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolderRecycler(binding);
    }

    /**
     * Método onBindViewHolder: enlaza la fuente de datos con el ViewHolder.
     * <p>
     * Obtiene el personaje en la posición actual y lo vincula al ViewHolder.
     * También establece un listener de clics para manejar la interacción del usuario.
     *
     * @param holder   ViewHolderRecycler que se utilizará para mostrar el personaje.
     * @param position Posición del personaje en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecycler holder, int position) {
        InfoPersonajes personajeActual = this.personajes.get(position);
        holder.bind(personajeActual);
        holder.itemView.setOnClickListener(view -> itemClicked(personajeActual, view));
    }

    /**
     * Método getItemCount: obtiene el número total de elementos en la lista.
     *
     * @return Número de elementos en la lista personajes.
     */
    @Override
    public int getItemCount() {
        return personajes.size();
    }

    /**
     * Método itemClicked: maneja el evento de clic en un elemento del RecyclerView.
     * <p>
     * Llama al método personajeClicked en MainActivity, pasando el personaje y la vista.
     * Este método está en MainActivity y se invoca aquí para manejar los eventos de clic.
     *
     * @param personaje El objeto InfoPersonajes que ha sido clicado.
     * @param view      La vista que ha sido clicada (se pasa aunque no siempre se use).
     */
    public void itemClicked(InfoPersonajes personaje, View view) {
        ((MainActivity) context).personajeCliked(personaje, view);
    }
}
