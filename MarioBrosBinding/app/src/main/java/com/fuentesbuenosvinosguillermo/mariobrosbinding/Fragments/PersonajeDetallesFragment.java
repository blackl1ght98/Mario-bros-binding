package com.fuentesbuenosvinosguillermo.mariobrosbinding.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuentesbuenosvinosguillermo.mariobrosbinding.MainActivity;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.R;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.FragmentPersonajeDetallesBinding;


public class PersonajeDetallesFragment extends Fragment {

  private FragmentPersonajeDetallesBinding binding;

//Metodo que crea la vista
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflar el layout para este fragmento
       binding= FragmentPersonajeDetallesBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }
//Metodo para cuando la vista es creada, tambien este metodo manda los datos puestos en la lista
    //que esta en otro fragmento obtiene los valores
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if(getArguments()!=null){
            int imagen = getArguments().getInt("imagen");
            String nombre= getArguments().getString("nombre");
            String descripcion = getArguments().getString("descripcion");
            String caracteristicas= getArguments().getString("caracteristicas");
            String habilidades= getArguments().getString("habilidades");
            binding.personajeimg.setImageResource(imagen);
            binding.nombrepnj.setText(nombre);
            binding.descripcion.setText(descripcion);
            binding.caracteristicas.setText(caracteristicas);
            binding.habilidades.setText(habilidades);
        }
        // Configurar el botón "Volver"
        binding.botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes definir qué hacer al pulsar el botón
                // Por ejemplo, volver a la actividad anterior
                requireActivity().onBackPressed(); // O puedes usar FragmentManager para manejar el back stack
            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        //Cambia el titulo del actionbar
//        if(getActivity()!=null){
//            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Detalles del personaje");
//        }
//    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Forzar la actualización del ícono del menú hamburguesa
//        ((MainActivity) getActivity()).getDrawerToggle().syncState();
//        // Mostrar el botón de hamburguesa
//        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
//    }
@Override
public void onStart() {
    super.onStart();
    if (getActivity() != null) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle("Detalles del personaje");
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}


    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() != null) {
            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mainActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }





}