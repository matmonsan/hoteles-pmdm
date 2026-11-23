package com.ejemplo.hospedajes.ui

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavDestination
import com.ejemplo.hospedajes.navegacion.Rutas
import com.ejemplo.hospedajes.navegacion.Seccion
import com.ejemplo.hospedajes.navegacion.esSeccion
import com.ejemplo.hospedajes.navegacion.seccionesMenu

// UD4, apartado 8
val seccionesBarra = seccionesMenu.take(3)   // Inicio, Mis hoteles, Explorar

@Composable
fun BarraInferior(
    destino: NavDestination?,
    numHoteles: Int,
    onSeccionClick: (Seccion) -> Unit
) {
    NavigationBar {
        seccionesBarra.forEach { seccion ->
            NavigationBarItem(
                selected = destino.esSeccion(seccion),
                onClick = { onSeccionClick(seccion) },
                icon = {
                    if (seccion.ruta == Rutas.MIS_HOTELES) {
                        BadgedBox(badge = { Badge { Text("$numHoteles") } }) {
                            Icon(seccion.icono, contentDescription = null)
                        }
                    } else {
                        Icon(seccion.icono, contentDescription = null)
                    }
                },
                label = { Text(seccion.titulo) }
            )
        }
    }
}
