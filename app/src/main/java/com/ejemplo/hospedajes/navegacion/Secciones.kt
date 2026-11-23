package com.ejemplo.hospedajes.navegacion

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavDestination

// UD4, apartados 7.3 y 7.4

data class Seccion(
    val ruta: String,
    val titulo: String,
    val icono: ImageVector
)

// Las tres primeras van también en la barra inferior (UD4, apartado 8.1)
val seccionesMenu = listOf(
    Seccion(Rutas.INICIO, "Inicio", Icons.Default.Home),
    Seccion(Rutas.MIS_HOTELES, "Mis hoteles", Icons.Default.Favorite),
    Seccion(Rutas.EXPLORAR, "Explorar", Icons.Default.Place),
    Seccion(Rutas.ACERCA_DE, "Acerca de", Icons.Default.Info)
)

// ¿El destino actual es esta sección?
fun NavDestination?.esSeccion(seccion: Seccion): Boolean =
    this?.route == seccion.ruta

// ¿El destino actual es alguna de las secciones del menú?
fun NavDestination?.esNivelSuperior(): Boolean =
    seccionesMenu.any { this.esSeccion(it) }

// Cambia de sección sin acumular pantallas en la pila
fun NavController.irASeccion(ruta: String) {
    navigate(ruta) {
        popUpTo(Rutas.INICIO) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
