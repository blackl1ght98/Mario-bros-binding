package com.fuentesbuenosvinosguillermo.mariobrosbinding.Recyclerview;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.databinding.PersonajesCardviewBinding;
import com.fuentesbuenosvinosguillermo.mariobrosbinding.InfoPersonajes;
import com.squareup.picasso.Picasso;

/**
 * Para que una clase pueda ser un viewholder que nutra al RecyclerView tenemos que hacer que herede de una clase de
 * android para que tenga esa funcion, la clase de la que extendera es RecyclerView.ViewHolder.
 * Como estamos haciendo uso del binding necesitamos pasarle una clase como variable para acceder a esta funcion de binding
 * */
/**¿Por que si el xml se llama personajes_cardview.xml el binding genera una variable tipo CamelCase PersonajesCardviewBinding?
 * El motivo es debido a una convencion de nombres que sigue android para garantizar que las clases de binding sean validas
 * y sigan los estandares de java.
 *
 * */
public class ViewHolderRecycler extends RecyclerView.ViewHolder {
private final PersonajesCardviewBinding binding;
/**
 * Para continuar creando el binding hacemos el constructor, para ello hacemos clic derecho sobre una parte libre del
 * IDE de android studio le damos a Generate y dentro le damos a constructor se nos abrira una vetana que podremos
 * poner las variables que tengamos declaradas en la clase en el constructor.
 * Cuando creamos el constructor por defecto nos lo crea asi:
 *  public ViewHolderRecycler( @NonNull  View itemView, PersonajesCardviewBinding binding) {
 *         super(itemView);
 *         this.binding = binding;
 *     }
 *  Cuando usamos el binding el constructor anterior cambia pero solo un poco y con el cambio queda asi:
 * public ViewHolderRecycler(  PersonajesCardviewBinding binding) {
 *         super(binding.getRoot());
 *         this.binding = binding;
 *     }
 *     El cambio que se ha echo es quitar el View itemView, y en  super(itemView) esto se cambia por esto super(binding.getRoot());
 *     que con el binding mas la palabra clave super esto binding.getRoot() se comunica con la clase padre que sera la clase de java
 *     que controle el binding
 * */
    public ViewHolderRecycler(  PersonajesCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
/**
 *A continuacion creamos un metodo para hacer el binding entre el layout y la clase java aqui la clase java seria infopersonajes y
 * el layout es personajes_cardview.xml
 * */
//   public void bind(InfoPersonajes personaje){
///**
// * Para las imagenes vamos a usar una dependecia llamada picaso para usarla lo primero es instalarla poniendo esta linea:
// * implementation ("com.squareup.picasso:picasso:2.71828") en el archivo build.gradle.kts a nivel de app y cuando lo pongamos
// * nos pedira que sincronicemos pues sincronizamos.
// * */
///**
// * Con esta linea que vemos a continuacion vemos lo util que es el realizar el binding nos evita tener que declarar esto ImageView imageView;
// * y luego llamar a esa variable y asignarle esto   imageView=itemView.findViewById(R.id.imageView); esto es lo que se encarga de hacer el binding
// * por nosotros
// * */
//      // Picasso.get().load(personaje.getImagen()).into(binding.image);
//       //Aqui continuamos viendo las ventajas del binding esta linea que parece simple esta haciendo 2 cosas por detras obteniendo el
//       //nombre del personaje y despues asignarlo al campo sin necesita de mencionar a que campo asignarlo porque con el binding ya lo
//       //sabe donde poner.
//       //Como solo en la primera pantalla queremos poner la imagen y el nombre se pone esto pero que si quisiesemos mostrar mas informacion
//       //seria hacer lo mismo
//       binding.image.setImageResource(personaje.getImagen());
//       binding.nombrepersonaje.setText(personaje.getNombre());
//       //Esta linea asegura que los cambios se apliquen
//       binding.executePendingBindings();
//
//
// }
public void bind(InfoPersonajes personaje) {
    Log.d("ViewHolder", "Nombre: " + personaje.getNombre() + ", Imagen: " + personaje.getImagen());
    binding.image.setImageResource(personaje.getImagen());
    binding.nombrepersonaje.setText(personaje.getNombre());
    binding.executePendingBindings();
}

}
