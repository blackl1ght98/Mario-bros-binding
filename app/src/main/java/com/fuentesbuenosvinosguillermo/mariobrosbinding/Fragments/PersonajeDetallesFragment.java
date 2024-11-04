package com.fuentesbuenosvinosguillermo.mariobrosbinding.Fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.MainActivity;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.R;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.FragmentPersonajeDetallesBinding;

public class PersonajeDetallesFragment extends Fragment {

    /**
     * Binding para acceder a los elementos de la interfaz de usuario definidos en fragment_personaje_detalles.xml
     */
    private FragmentPersonajeDetallesBinding binding;

    /**
     * Método onCreateView: Crea y devuelve la vista asociada al fragmento.
     * Aquí, inflamos el layout usando View Binding para acceder a sus elementos directamente.
     * @param inflater el inflador de layout para crear vistas en el fragmento
     * @param container el contenedor que contiene la vista del fragmento
     * @param savedInstanceState el estado guardado previamente, si existe
     * @return la vista raíz inflada
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla el layout fragment_personaje_detalles.xml y lo asocia a 'binding'
        binding = FragmentPersonajeDetallesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Método onViewCreated: Ejecuta lógica adicional después de que la vista ha sido creada.
     * Este método obtiene los datos del personaje pasados como argumentos al fragmento y los muestra en la interfaz.
     * @param view la vista raíz del fragmento
     * @param savedInstanceState el estado guardado previamente, si existe
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Comprueba si el fragmento recibió argumentos (datos del personaje)
        if (getArguments() != null) {
            // Obtiene los datos del personaje desde los argumentos
            int imagen = getArguments().getInt("imagen");
            String nombre = getArguments().getString("nombre");
            String descripcion = getArguments().getString("descripcion");
            String caracteristicas = getArguments().getString("caracteristicas");
            String habilidades = getArguments().getString("habilidades");

            // Asigna los datos a los elementos de la interfaz usando View Binding
            binding.personajeimg.setImageResource(imagen);
            binding.nombrepnj.setText(nombre);
            binding.descripcion.setText(descripcion);
            binding.caracteristicas.setText(caracteristicas);
            binding.habilidades.setText(habilidades);
        }

        // Configura el botón "Volver" para regresar al fragmento anterior en la pila de navegación
        binding.botonVolver.setOnClickListener(v -> {
            // Obtiene el controlador de navegación y ejecuta el método para volver a la pantalla anterior
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.popBackStack();
        });
    }

    /**
     * Método onStart: Se ejecuta cuando el fragmento se vuelve visible al usuario.
     * Configura el título de la barra de acción para indicar que se están mostrando los detalles del personaje.
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            // Cambia el título de la barra de acción al título personalizado
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.getSupportActionBar().setTitle("Detalles del personaje");
        }
    }

    /**
     * Método onResume: Se ejecuta cuando el fragmento entra en estado "Resumido" (visible y activo).
     * Este método sincroniza el menú lateral y muestra el botón de retroceso en la barra de acción.
     */
    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();

        if (mainActivity != null) {
            // Sincroniza el estado del menú lateral para reflejar cambios visuales
            mainActivity.getDrawerToggle().syncState();
            // Muestra el botón de retroceso en la barra de acción
            mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
