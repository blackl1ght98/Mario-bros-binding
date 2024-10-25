package com.fuentesbuenosvinosguillermo.mariobrosbinding.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuentesbuenosvinosguillermo.mariobrosbinding.InfoPersonajes;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.MainActivity;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.R;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.Recyclerview.AdapterRecyclerView;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.FragmentPersonajesListBinding;


import java.util.ArrayList;

/**
 * Los fragments son como si fuese una creacion de una actividad completa pero no es asi
 * son como partes que se unen a la actividad principal esto permite que la aplicacion tenga
 * un rendimiento mayor y sea reutilizable, este fragment es el que contiene el RecyclerView
 * y como lo contiene es necesario pasarle una serie de variables
 * */
public class Personajes_list_Fragment extends Fragment {
    /***
     * REQUISITO IMPORTANTE:
     * PARA QUE EL BINDING FUNCIONE EL LAYOUT TIENE QUE SER DE TIPO androidx.coordinatorlayout.widget.CoordinatorLayout
     * */
    /**
     * Como este fragmento contiene el RecyclerView necesita estas variables la primera para el binding, la segunda la fuente de datos,
     * y la ultima el adapter que es el que tiene la logica
     */
    private FragmentPersonajesListBinding binding; //Binding para el layout
    private ArrayList<InfoPersonajes> personajes;// Fuente de datos
    private AdapterRecyclerView adapter;

//Metodo que crea la vista.

    /**
     * Si no usas el binding se queda tal y como te lo genera automaticamente:
     *
     * @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
     * Bundle savedInstanceState) {
     * // Inflate the layout for this fragment
     * return inflater.inflate(R.layout.fragment_personajes_list_, container, false);
     * }
     * Pero como lo vamos a usar tiene otro aspectos que encapsula parte de este codigo
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**
         * Aquí lo que hacemos es inflar el layout con los datos que se tienen que mostrar
         *
         * @param inflater        El objeto LayoutInflater que se usa para inflar el XML en un objeto View.
         * @param container       El ViewGroup donde se añadirá el fragmento. Este es el contenedor padre.
         * @param savedInstanceState  Un Bundle que contiene el estado previamente guardado del fragmento, si existe.
         *                            Se utiliza para restaurar la información si el fragmento se recrea.
         *
         * El método devuelve la vista raíz del layout inflado.
         */
        binding = FragmentPersonajesListBinding.inflate(inflater, container, false);
        return binding.getRoot(); // Devuelve la vista raíz del binding.

    }

    //Metodo que tiene la logica de cuando la vista se crea
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Inicializar la lista de personajes
        loadPersonajes();

        // Configurar el RecyclerView
        adapter = new AdapterRecyclerView(personajes, getActivity());
        binding.personajesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.personajesRecyclerview.setAdapter(adapter);


    }


    //Metodo para cargar los personajes
    private void loadPersonajes() {
        personajes = new ArrayList<InfoPersonajes>();
        personajes.add(new InfoPersonajes("Mario",
                "Salto, fuerza, habilidades de fuego",
                "Mario es un fontanero valiente que lucha para rescatar a la Princesa Peach y derrotar a Bowser.",
                "Amigo leal, optimista, valiente.",
                R.drawable.mario));

        personajes.add(new InfoPersonajes("Luigi",
                "Salto alto, habilidades de fuego, gran velocidad",
                "Luigi es el hermano de Mario, conocido por ser un poco más alto y por su personalidad más tímida.",
                "Leal, un poco miedoso, pero valiente cuando es necesario.",
                R.drawable.luigi));

        personajes.add(new InfoPersonajes("Peach",
                "Uso de paraguas, habilidades mágicas",
                "La Princesa Peach es la gobernante del Reino Champiñón y a menudo es secuestrada por Bowser.",
                "Amable, compasiva y fuerte, a menudo toma las riendas de la acción.",
                R.drawable.peach));

        personajes.add(new InfoPersonajes("Toad",
                "Agilidad, habilidades de apoyo",
                "Toad es un leal sirviente de la Princesa Peach que ayuda a Mario en su aventura.",
                "Enérgico, servicial y valiente, a pesar de su pequeño tamaño.",
                R.drawable.toad));


    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lista de personajes");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Forzar la actualización del ícono del menú hamburguesa
        //DESCOMENTAR
      //  ((MainActivity) getActivity()).getDrawerToggle().syncState();
        // Mostrar el botón de hamburguesa
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}