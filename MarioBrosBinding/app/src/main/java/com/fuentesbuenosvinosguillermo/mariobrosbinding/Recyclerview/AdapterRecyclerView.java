package com.fuentesbuenosvinosguillermo.mariobrosbinding.Recyclerview;
/**
 * Para que un recycler view funcione necesita varias cosas:
 * - 1º La fuente de datos que es lo que mostrara el recyclerview
 * - 2º El viewholder que es lo que mostrara el recyclerview
 * - 3º El Adapter que es la logica del recyclerview me refiero que es lo que va a pasar si alguien hace clic sobre un item, si se puede modificar..
 * */

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
 * Para crear el adapter creamos una clase java normal al igual que con el viewholder y tenemos que decir que hereda de: RecyclerView.Adapter
 * pero esta herencia por si  sola no funciona necesita algo mas ese algo mas que necesita es como los datos se mostrara quedando la herencia
 * asi RecyclerView.Adapter<ViewHolderRecycler>, este tipo de herencia tiene unos metodos por defecto que podemos autoimportar.
 * Estos metodos cada uno maneja una logica diferente a continuacion vamos a ver como funciona cada uno de ellos
 * */
public class AdapterRecyclerView extends RecyclerView.Adapter<ViewHolderRecycler> {
    //Lo primero es pasarle la fuente de datos y el contexto estos 2 parametros se almacenara en variables
    private  final ArrayList<InfoPersonajes> personajes;
    private  final Context context;
    //Estas variables necesitan ser inicializadas para ello lo ponemos en un constructor

    public AdapterRecyclerView(ArrayList<InfoPersonajes> personajes, Context context) {
        this.personajes = personajes;
        this.context = context;
    }
//Este es un metodo que crea el viewholder
@NonNull
@Override
public ViewHolderRecycler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    /**
     * Aquí estamos utilizando Data Binding para inflar el layout `personajes_cardview.xml` de manera eficiente.
     *
     * En lugar de usar `LayoutInflater.inflate(R.layout.personajes_cardview, parent, false)`, que sería el método
     * tradicional, usamos el binding generado automáticamente: `PersonajesCardviewBinding.inflate()`.
     *
     * La función `inflate()` necesita tres parámetros:
     * 1. **LayoutInflater**: Lo obtenemos llamando a `LayoutInflater.from(parent.getContext())`, que es el contexto del padre.
     * 2. **ViewGroup parent**: Es el contenedor donde se va a agregar la vista inflada, en este caso, el RecyclerView.
     * 3. **boolean attachToParent**: Es `false` porque el RecyclerView se encargará de adjuntar la vista.
     *
     * Una vez inflado, devolvemos una nueva instancia de nuestro ViewHolder (`ViewHolderRecycler`) y le pasamos el binding.
     */
    PersonajesCardviewBinding binding = PersonajesCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolderRecycler(binding);
}

    // Método para enlazar los datos con el ViewHolder
    @Override
    public void onBindViewHolder(@NonNull ViewHolderRecycler holder, int position) {
        // Línea 1: Obtiene el personaje en la posición actual de la lista de personajes.
        InfoPersonajes personajeActual = this.personajes.get(position);

        // Línea 2: Enlaza el personaje actual con el ViewHolder.
        holder.bind(personajeActual);
        holder.itemView.setOnClickListener(view -> itemClicked(personajeActual,view));
    }
//Metodo que obtiene el tamaño del array
    @Override
    public int getItemCount() {
        return personajes.size();
    }

//Metodo que hemos creado para controlar el clic
    public void itemClicked(InfoPersonajes personaje, View view){
        ((MainActivity) context).personajeCliked(personaje,view);
    }
}
