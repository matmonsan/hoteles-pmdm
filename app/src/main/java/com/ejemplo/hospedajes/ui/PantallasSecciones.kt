package com.ejemplo.hospedajes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// Pantallas sencillas de las secciones del menú (UD4, apartado 7.3)

@Composable
fun PantallaInicio(
    nombreUsuario: String,
    numHoteles: Int,
    onVerMisHoteles: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Text("Hola, $nombreUsuario", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Tienes $numHoteles hospedajes registrados.",
            style = MaterialTheme.typography.bodyLarge
        )
        Button(onClick = onVerMisHoteles) { Text("Ver mis hoteles") }
    }
}

@Composable
fun PantallaExplorar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Icon(Icons.Default.Place, contentDescription = null, modifier = Modifier.size(64.dp))
        Text(
            text = "Aquí se mostrarán los hospedajes de todos los usuarios.",
            textAlign = TextAlign.Center
        )
        Text(
            text = "La lista de elementos comunes se construye en la UD5.",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun PantallaAcercaDe() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hospedajes\nAplicación de ejemplo de las UD3 y UD4\nProgramación Multimedia y Dispositivos Móviles",
            textAlign = TextAlign.Center
        )
    }
}
