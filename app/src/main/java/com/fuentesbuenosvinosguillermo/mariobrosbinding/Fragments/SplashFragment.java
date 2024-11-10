package com.fuentesbuenosvinosguillermo.mariobrosbinding.Fragments;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fuentesbuenosvinosguillermo.mariobrosbinding.R;

/**
 * SplashFragment muestra una pantalla de bienvenida al iniciar la aplicación.
 * La pantalla se oculta automáticamente después de un retraso de 3 segundos,
 * redirigiendo al usuario al fragmento de lista de personajes.
 */
public class SplashFragment extends Fragment {

    /**
     * Infla el layout 'fragment_splashs.xml'.
     * Oculta la ActionBar cuando se muestra el fragmento y, después de un retraso,
     * navega al fragmento de lista de personajes.
     *
     * @param inflater           El LayoutInflater usado para inflar el layout del fragmento.
     * @param container          El contenedor en el cual el fragmento es mostrado.
     * @param savedInstanceState Estado guardado del fragmento, si lo hubiera.
     * @return La vista inflada para el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splahs, container, false);

        // Verificar si la ActionBar está disponible antes de ocultarla
        if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) requireActivity()).getSupportActionBar().hide();
        }

        // Delay de 3 segundos antes de redirigir
        new Handler().postDelayed(() -> {
            // Verificar y mostrar la ActionBar nuevamente cuando se navega fuera del fragmento
            if (((AppCompatActivity) requireActivity()).getSupportActionBar() != null) {
                ((AppCompatActivity) requireActivity()).getSupportActionBar().show();
            }

            // Navegar al siguiente fragmento
            NavController navController = NavHostFragment.findNavController(SplashFragment.this);
            navController.navigate(R.id.personajes_list_Fragment);
        }, 3000);

        return view;
    }

    /**
     * onResume: Llamado cuando el fragmento es visible para el usuario.
     * Vuelve a ocultar la ActionBar en caso de que el usuario regrese al fragmento.
     */
    @Override
    public void onResume() {
        super.onResume();
        AppCompatActivity activity = ((AppCompatActivity) requireActivity());
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }
    }

    /**
     * onPause: Llamado cuando el fragmento ya no está en primer plano.
     * Muestra nuevamente la ActionBar cuando el usuario navega fuera del fragmento.
     */
    @Override
    public void onPause() {
        super.onPause();
        AppCompatActivity activity = ((AppCompatActivity) requireActivity());
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
        }
    }
}
