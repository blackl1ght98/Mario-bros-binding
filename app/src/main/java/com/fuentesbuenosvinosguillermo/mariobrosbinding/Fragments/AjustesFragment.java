package com.fuentesbuenosvinosguillermo.mariobrosbinding.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.MainActivity;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.FragmentAjustesBinding;
import java.util.Locale;

/**
 * Fragmento encargado de configurar el idioma de la aplicación.
 * Permite cambiar entre español e inglés mediante un Switch.
 */
public class AjustesFragment extends Fragment {
    private FragmentAjustesBinding binding;

    /**
     * Se ejecuta al crear la vista del fragmento.
     * Usa View Binding para inflar el layout `fragment_ajustes.xml`, que contiene un Switch y un texto.
     * Carga la preferencia de idioma guardada y configura el Switch para cambiar entre idiomas.
     *
     * @param inflater Inflador para crear la vista del fragmento.
     * @param container Contenedor padre al que se adjuntará el fragmento.
     * @param savedInstanceState Estado previamente guardado del fragmento (si aplica).
     * @return La vista inflada usando View Binding.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Usa View Binding para inflar la vista
        binding = FragmentAjustesBinding.inflate(inflater, container, false);

        // Cargar la preferencia de idioma almacenada en SharedPreferences, por defecto "es" (español)
        SharedPreferences prefs = requireActivity().getSharedPreferences("AppSettings", MODE_PRIVATE);
        String language = prefs.getString("Spanish", "es");

        // Configura el estado inicial del Switch según la preferencia de idioma
        binding.language.setChecked(language.equals("es"));

        // Configura el cambio de idioma según el estado del Switch
        binding.language.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                setLocale("es"); // Cambiar a español
            } else {
                setLocale("en"); // Cambiar a inglés
            }
        });

        return binding.getRoot();
    }

    /**
     * Se ejecuta cuando el fragmento se muestra en pantalla.
     * Configura el título de la barra de acción como "Ajustes" y oculta el botón de navegación lateral.
     */
    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Ajustes");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * Configura el idioma de la aplicación según el código de idioma proporcionado.
     * Cambia el idioma de la interfaz de usuario, guarda la preferencia en `SharedPreferences`,
     * y reinicia la actividad para aplicar el cambio de idioma inmediatamente.
     *
     * @param lang Código del idioma (por ejemplo, "es" para español, "en" para inglés).
     */
    public void setLocale(String lang) {
        // Crear un objeto Locale con el idioma especificado
        Locale locale = new Locale(lang);

        // Establecer el Locale como predeterminado
        Locale.setDefault(locale);

        // Configurar la aplicación para usar el nuevo idioma
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);

        // Guardar la preferencia de idioma en SharedPreferences para que persista entre sesiones
        SharedPreferences.Editor editor = requireActivity().getSharedPreferences("AppSettings", MODE_PRIVATE).edit();
        editor.putString("Spanish", lang);
        editor.apply();

        // Reiniciar la actividad para aplicar el cambio de idioma
        requireActivity().recreate();
    }

    /**
     * Método que se ejecuta cuando el fragmento vuelve a estar visible en pantalla.
     * Configura el botón de retroceso en la barra de acción y sincroniza el estado del menú lateral.
     */
    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.getDrawerToggle().syncState();
        mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Con botón de retroceso
    }

    /**
     * Método que se ejecuta al destruir la vista del fragmento.
     * Libera el binding para evitar pérdidas de memoria.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
