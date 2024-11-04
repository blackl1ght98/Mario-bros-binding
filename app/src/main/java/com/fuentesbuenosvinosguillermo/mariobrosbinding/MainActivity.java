package com.fuentesbuenosvinosguillermo.mariobrosbinding;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.datosAplicacion.personajeData;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;

/**
 * Clase principal que se encarga de coordinar toda la lógica de la aplicación.
 * Gestiona la navegación entre fragmentos y la configuración del menú lateral.
 */
public class MainActivity extends AppCompatActivity {
    // Controla la navegación entre fragmentos
    private NavController navController;
    // Binding con el layout activity_main.xml
    private ActivityMainBinding binding;
    // Configuración del menú lateral
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    // Configuración de la barra de acción
    private AppBarConfiguration appBarConfiguration;
    // Llamada a la lista de personajes
    private List<InfoPersonajes> personajes;

    /**
     * Método que se ejecuta al crear la actividad.
     * Inicializa los componentes de la interfaz, configura el menú lateral y la navegación.
     *
     * @param savedInstanceState Estado anterior de la actividad, se utiliza para restaurar la actividad.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Uso del binding para llamar a la actividad principal
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // Llamada al toolbar que está en activity_main.xml
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        // Llamada al menú lateral que está en activity_main.xml
        drawerLayout = binding.main;
        // Configuración del contenedor de navegación entre fragmentos
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Configuración de la barra de acción y menú lateral
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.personajes_list_Fragment)
                .setOpenableLayout(drawerLayout)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Obtiene la lista de personajes desde personajeData
        personajes = personajeData.obtenerPersonajes(this);
        // Se muestra al iniciar la lista de personajes
        Snackbar.make(findViewById(R.id.main), getString(R.string.bienvenido), Snackbar.LENGTH_LONG).show();

        // Añadir personajes al submenú dinámicamente del menú lateral
        NavigationView navigationView = binding.navigationView;
        Menu menu = navigationView.getMenu();
        SubMenu subMenuPersonajes = menu.findItem(R.id.personajes_menu).getSubMenu();

        for (int i = 0; i < personajes.size(); i++) {
            InfoPersonajes personaje = personajes.get(i);
            if (subMenuPersonajes != null) {
                subMenuPersonajes.add(0, i, Menu.NONE, personaje.getNombre()).setIcon(R.drawable.person_outline);
            }
        }

        // Configura lo que ocurre al hacer clic en algún item del menú lateral
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                navController.navigate(R.id.personajes_list_Fragment);
            } else if (id == R.id.nav_settings) {
                navController.navigate(R.id.ajustesFragment);
            } else if (id >= 0 && id < personajes.size()) {
                InfoPersonajes personajeSeleccionado = personajes.get(id);
                Bundle bundle = new Bundle();
                bundle.putString("nombre", personajeSeleccionado.getNombre());
                bundle.putString("descripcion", personajeSeleccionado.getDescripcion());
                bundle.putString("habilidades", personajeSeleccionado.getHabilidades());
                bundle.putString("caracteristicas", personajeSeleccionado.getCaracteristicas());
                bundle.putInt("imagen", personajeSeleccionado.getImagen());
                navController.navigate(R.id.personajeDetallesFragment, bundle);
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

        // Controla la visibilidad del botón de retroceso en la barra de acción
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.personajes_list_Fragment) {
                toggle.setDrawerIndicatorEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            } else {
                toggle.setDrawerIndicatorEnabled(false);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configura el comportamiento de la flecha de retroceso
        toolbar.setNavigationOnClickListener(v -> onSupportNavigateUp());

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    setEnabled(false);
                    onBackPressed();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Método encargado de gestionar la acción de la flecha de retroceso.
     *
     * @return true si la navegación fue exitosa, false de lo contrario.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    /**
     * Método que se llama cuando se hace clic en un personaje.
     * Envía los detalles del personaje al fragmento de detalles.
     *
     * @param personaje Personaje que ha sido seleccionado.
     * @param view Vista desde la cual se realiza el clic.
     */
    public void personajeCliked(InfoPersonajes personaje, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("nombre", personaje.getNombre());
        bundle.putString("descripcion", personaje.getDescripcion());
        bundle.putString("caracteristicas", personaje.getCaracteristicas());
        bundle.putString("habilidades", personaje.getHabilidades());
        bundle.putInt("imagen", personaje.getImagen());
        navController.navigate(R.id.personajeDetallesFragment, bundle);
    }

    /**
     * Método que se llama para crear el menú de opciones.
     *
     * @param menu Menú que se va a crear.
     * @return true si se creó el menú correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método que devuelve el ActionBarDrawerToggle para permitir su uso en otras clases.
     *
     * @return ActionBarDrawerToggle que controla el menú lateral.
     */
    public ActionBarDrawerToggle getDrawerToggle() {
        return toggle;
    }

    /**
     * Método que se llama cuando se selecciona un item del menú de opciones.
     *
     * @param item Item que se seleccionó.
     * @return true si el item fue manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            new AlertDialog.Builder(this)
                    .setTitle("Acerca de...")
                    .setMessage("Aplicación desarrollada por Guillermo Fuentes Buenosvinos. Versión 1.0.")
                    .setPositiveButton(android.R.string.yes, (dialogInterface, i) -> Log.d("Mensaje", "Acción ejecutada con éxito"))
                    .show();
        } else if (id == R.id.settings) {
            navController.navigate(R.id.ajustesFragment);
        }
        return super.onOptionsItemSelected(item);
    }
}
