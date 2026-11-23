# hoteles-pmdm

Aplicación de ejemplo de las UD3 (Listas, diálogos y arquitectura MVVM) y UD4 (Navegación y menús) del módulo Programación Multimedia y Dispositivos Móviles.

Gestión de hospedajes con Jetpack Compose: lista de hoteles, detalle, alta, edición y borrado con los datos en memoria (UD3), navegación con `NavHost`, barra superior con menú de opciones, menú lateral, barra inferior y pantallas de acceso con sesión simulada (UD4).

## Ramas

| Rama | Contenido |
|---|---|
| `ud3` | La aplicación tal como queda al terminar la UD3 |
| `ud4` | La aplicación de la UD3 con todo lo de la UD4 |

## Cómo abrirla

Abre la carpeta del proyecto en Android Studio y ejecuta la configuración `app` en un emulador o dispositivo. Las imágenes de los hoteles se descargan de internet, así que el dispositivo necesita conexión.

Al arrancar se ve el inicio de sesión. La sesión está simulada: se entra con cualquier correo que contenga `@` y cualquier contraseña de al menos 6 caracteres.

## Archivos y apartado donde se explican

| Archivo | Contenido | Apartado |
|---|---|---|
| `data/Hotel.kt` | Modelo de datos | UD3 3.2 |
| `data/HotelRepository.kt` | Repositorio en memoria con el CRUD | UD3 6.2 |
| `data/AuthRepository.kt` | `Usuario` y sesión simulada | UD4 7.5 y 9.2 |
| `logica/HospedajeUiState.kt` | Estado de la interfaz, sin el hotel seleccionado | UD3 6.3, UD4 4.6 |
| `logica/HospedajeViewModel.kt` | ViewModel con una función por evento, sin funciones de navegación | UD3 6.4, UD4 4.6 |
| `logica/AppPantallas.kt` | `NavHost` con los grafos de acceso y principal, menú lateral, `Scaffold` común, diálogos y `Snackbar` | UD4 4.6, 6 a 9 |
| `navegacion/Rutas.kt` | Rutas de todas las pantallas y de los grafos | UD4 3.2, 4.1 y 9.1 |
| `navegacion/Secciones.kt` | Secciones del menú, nivel superior e `irASeccion` | UD4 7.3 y 7.4 |
| `ui/BarraSuperior.kt` | `TopAppBar` con ≡ o ← y menú ⋮ | UD4 5 y 6 |
| `ui/BarraInferior.kt` | `NavigationBar` con contador de hoteles | UD4 8 |
| `ui/MenuLateral.kt` | Cabecera con el usuario y opciones del menú lateral | UD4 7.5 |
| `ui/PantallasSecciones.kt` | Inicio, Explorar y Acerca de | UD4 7.3 |
| `ui/PantallaPrincipal.kt` | Lista de «Mis hoteles» | UD3 3, UD4 4.6 |
| `ui/PantallaDetalle.kt` | Detalle con Editar y Eliminar | UD3 8.3, UD4 4.6 |
| `ui/AddEditHotelDialog.kt` | Formulario de alta y edición con validación | UD3 7.3 y 7.4 |
| `ui/ConfirmDeleteDialog.kt` | Confirmación de borrado | UD3 7.2 |
| `ui/acceso/PantallasAcceso.kt` | Inicio de sesión, registro y recuperación de contraseña | UD4 9.3 |
| `MainActivity.kt` | Llama a `AppPantallas()` | UD3 5.6 |
