# hoteles-pmdm

Aplicación de ejemplo de la UD3 (Listas, diálogos y arquitectura MVVM) del módulo Programación Multimedia y Dispositivos Móviles.

Gestión de hospedajes con Jetpack Compose: lista de hoteles, detalle, alta, edición y borrado, con los datos en memoria.

## Cómo abrirla

Abre la carpeta del proyecto en Android Studio y ejecuta la configuración `app` en un emulador o dispositivo. Las imágenes se descargan de internet, así que el dispositivo necesita conexión.

## Archivos y apartado de la UD3 donde se explican

| Archivo | Contenido | Apartado |
|---|---|---|
| `Hotel.kt` | Modelo de datos | 3.2 |
| `HotelRepository.kt` | Repositorio en memoria con el CRUD | 6.2 |
| `HospedajeUiState.kt` | Estado de la interfaz | 6.3 y 6.6 |
| `HospedajeViewModel.kt` | ViewModel con una función por evento | 6.4 y 6.6 |
| `Dialogos.kt` | Confirmación de borrado y formulario con validación | 7.2, 7.3 y 7.4 |
| `PantallaPrincipal.kt` | Lista de hoteles con `Scaffold` y botón + | 8.3 |
| `PantallaDetalle.kt` | Detalle con flecha de volver, Editar y Eliminar | 8.3 |
| `AppPantallas.kt` | Elige la pantalla visible y muestra diálogos y `Snackbar` | 5 y 8.3 |
| `MainActivity.kt` | Llama a `AppPantallas()` | 5.6 |
