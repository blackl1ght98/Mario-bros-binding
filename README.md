# Mi Primera Aplicación en Android Studio

**Descripción:**

Esta aplicación es una introducción sencilla a Android Studio y sus características principales. Permite ver los nombres de los personajes de Mario Bros y explorar sus habilidades. Además, incluye funcionalidades de navegación, personalización de la interfaz y configuración de idioma. A continuación, se detallan las características clave de la aplicación.

## Características

- **Uso de ViewBinding y DataBinding**:  
  Estas herramientas permiten un acceso más rápido y eficiente a las vistas y propiedades del modelo, facilitando la actualización y gestión de la interfaz de usuario.

- **RecyclerView y CardView**:  
  La aplicación muestra una lista de personajes en la que cada personaje está contenido dentro de un **CardView**. Al hacer clic en un personaje, se puede ver más información sobre él, aprovechando la capacidad de **RecyclerView** para manejar grandes cantidades de datos de manera eficiente.

- **Navegación entre Fragmentos**:  
  Se ha implementado la navegación entre fragmentos utilizando **NavHostFragment**, lo que mejora la gestión de los recursos y facilita la transición entre diferentes pantallas sin necesidad de recargar la actividad completa.

- **DrawerLayout (Menú Lateral)**:  
  La aplicación incluye un **DrawerLayout** que permite a los usuarios acceder rápidamente a diferentes secciones de la aplicación, como la lista de personajes, los ajustes y más.

- **Toolbar**:  
  Se ha añadido una **Toolbar** en la parte superior de la pantalla, que permite ofrecer opciones de navegación o configuraciones adicionales, como es común en la mayoría de las aplicaciones móviles modernas.

- **Cambio de Idioma**:  
  La aplicación permite al usuario cambiar el idioma entre **inglés** y **español**, adaptándose a diferentes usuarios y sus preferencias.

## Estructura de la Navegación

La aplicación emplea un **NavHostFragment** en el archivo XML de la actividad principal. Este contenedor facilita la navegación entre fragmentos, proporcionando una experiencia de usuario fluida y eficiente.

## Dependencias y Tecnologías Utilizadas

- **ViewBinding**: Para la vinculación de vistas de manera eficiente.
- **DataBinding**: Para el enlace de datos entre la UI y el modelo.
- **RecyclerView**: Para mostrar una lista eficiente de elementos.
- **CardView**: Para mostrar la información de cada personaje en tarjetas.
- **NavController**: Para la navegación entre fragmentos.
- **DrawerLayout**: Para el menú lateral.
- **Toolbar**: Para una barra de herramientas personalizable.

## Instrucciones de Uso

1. **Clonar o descargar el repositorio**.
2. **Abrir el proyecto en Android Studio**.
3. **Ejecutar la aplicación** en un emulador o dispositivo físico.
