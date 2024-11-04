package com.fuentesbuenosvinosguillermo.mariobrosbinding.datosAplicacion;

import android.content.Context;

import com.fuentesbuenosvinosguillermo.mariobrosbinding.InfoPersonajes;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Clase de java que contiene la informacion que se mostrara de los personajes la razon por la que esta separada es para que el codigo no sea
 * extenso y con solo llamar a la clase y al metodo se muestren los personajes
 * */
public class personajeData {
    public static ArrayList<InfoPersonajes> obtenerPersonajes(Context context) {
        ArrayList<InfoPersonajes> personajes = new ArrayList<>();

        personajes.add(new InfoPersonajes(
                context.getString(R.string.mario_name),
                context.getString(R.string.mario_skills),
                context.getString(R.string.mario_description),
                context.getString(R.string.mario_traits),
                R.drawable.mario));

        personajes.add(new InfoPersonajes(
                context.getString(R.string.luigi_name),
                context.getString(R.string.luigi_skills),
                context.getString(R.string.luigi_description),
                context.getString(R.string.luigi_traits),
                R.drawable.luigi));

        personajes.add(new InfoPersonajes(
                context.getString(R.string.peach_name),
                context.getString(R.string.peach_skills),
                context.getString(R.string.peach_description),
                context.getString(R.string.peach_traits),
                R.drawable.peach));

        personajes.add(new InfoPersonajes(
                context.getString(R.string.toad_name),
                context.getString(R.string.toad_skills),
                context.getString(R.string.toad_description),
                context.getString(R.string.toad_traits),
                R.drawable.toad));

        return personajes;
    }
}
