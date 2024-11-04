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
import com.fuentesbuenosvinosguillermo.mariobrosbinding.datosAplicacion.personajeData;
import java.util.ArrayList;

/**
 * Personajes_list_Fragment es un fragmento que muestra una lista de personajes en un RecyclerView.
 * Utiliza View Binding para acceder a los elementos de la vista de manera segura y eficiente.
 */
public class Personajes_list_Fragment extends Fragment {

    private FragmentPersonajesListBinding binding;
    private ArrayList<InfoPersonajes> personajes;
    private AdapterRecyclerView adapter;

    /**
     * Crea y devuelve la vista para este fragmento.
     *
     * @param inflater El LayoutInflater utilizado para inflar el XML del fragmento. El layout es 'fragment_personajes_list_.xml'
     * @param container El ViewGroup donde se añadirá el fragmento.
     * @param savedInstanceState Un Bundle que contiene el estado previamente guardado del fragmento, si existe.
     * @return La vista raíz inflada para el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPersonajesListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Configura los componentes una vez que la vista ha sido creada.
     * Inicializa la lista de personajes, configura el RecyclerView con un layout vertical
     * y asigna un AdapterRecyclerView con los datos de personajes.
     *
     * @param view La vista creada.
     * @param savedInstanceState Estado previamente guardado del fragmento, si existe.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        personajes = personajeData.obtenerPersonajes(requireContext());
        adapter = new AdapterRecyclerView(personajes, getActivity());
        binding.personajesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.personajesRecyclerview.setAdapter(adapter);
    }

    /**
     * Ajusta el ActionBar cuando el fragmento se muestra por primera vez.
     * Establece el título en "Lista de personajes" y oculta el botón de retroceso.
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getContext().getString(R.string.lista_de_personajes));
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * Configura el menú de navegación (botón de hamburguesa) cuando el fragmento se reanuda.
     * Sincroniza el ícono de menú para que se actualice correctamente y oculta el botón de retroceso.
     */
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getDrawerToggle().syncState();
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}
