package com.fuentesbuenosvinosguillermo.mariobrosbinding;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
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
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    /**Controla la navegacion*/
    private NavController navController;
    /**Para usar el binding*/
    private ActivityMainBinding binding;
    /**Para configurar el menu lateral*/
    private DrawerLayout drawerLayout;
    /**Manejar el clic en el menu lateral*/
    private ActionBarDrawerToggle toggle;
    /**Inicializa la lista*/
    private ArrayList<InfoPersonajes> personajes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        /**Configura el binding para inflar la vista principal*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /**Configura la Toolbar como ActionBar*/
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        /**Inicializa el navcontroller*/
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Configuración de AppBarConfiguration para definir los destinos principales
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.personajes_list_Fragment, R.id.nav_home )
//                .setOpenableLayout(binding.main)
//                .build();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(binding.main) // Asegúrate de que esto sea tu DrawerLayout
                .build();

        // Asocia el controlador de navegación con el ActionBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // Configuración de DrawerLayout y el ActionBarDrawerToggle
        drawerLayout = binding.main;
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


// Asegúrate de que el toggle esté sincronizado con la Toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //----------------------------------------------------------------------------------------
        /**Localiza el componente de navegacion*/
        NavigationView navigationView = binding.navigationView;
       /**Configura el  escuchador de eventos cuando seleccionas algo*/
        navigationView.setNavigationItemSelectedListener(item -> {
            // Obtener la id del item

            int id = item.getItemId();
            // Si le damos a Home...
            if (id == R.id.nav_home) {
                //navegamos a la lista de personajes
                navController.navigate(R.id.personajes_list_Fragment);
            } else if (id == R.id.nav_settings) {
                // Muestra la vista de ajustes

            } else if (id >= 0 && id < personajes.size()) {
                // Obtener el personaje seleccionado
                InfoPersonajes personajeSeleccionado = personajes.get(id);

                // Crear un bundle para pasar los datos al fragmento
                Bundle bundle = new Bundle();
                bundle.putString("nombre", personajeSeleccionado.getNombre());
                bundle.putString("descripcion", personajeSeleccionado.getDescripcion());
                bundle.putString("habilidades", personajeSeleccionado.getHabilidades());
                bundle.putString("caracteristicas", personajeSeleccionado.getCaracteristicas());
                bundle.putInt("imagen", personajeSeleccionado.getImagen());

                // Navegar al fragmento de detalles
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.personajeDetallesFragment, bundle);
            }

            // Cierra el menú lateral
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });

       // Manejar el evento de retroceso
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Verifica si el Drawer está abierto
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    drawerLayout.close();
                } else {


                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
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

    // Método para manejar el clic de un personaje
    public void personajeCliked(InfoPersonajes personajes, View view) {
        Bundle bundle = new Bundle();
        bundle.putString("nombre", personajes.getNombre());
        bundle.putString("descripcion", personajes.getDescripcion());
        bundle.putString("caracteristicas", personajes.getCaracteristicas());
        bundle.putString("habilidades", personajes.getHabilidades());
        bundle.putInt("imagen", personajes.getImagen());

        // Navegar al fragmento de detalles del personaje
        Navigation.findNavController(view).navigate(R.id.personajeDetallesFragment, bundle);
    }


@Override
public boolean onSupportNavigateUp() {
    // Utiliza el método navigateUp del NavController
    return navController.navigateUp() || super.onSupportNavigateUp();
}



//    public ActionBarDrawerToggle getDrawerToggle() {
//        return toggle;
//    }




}
