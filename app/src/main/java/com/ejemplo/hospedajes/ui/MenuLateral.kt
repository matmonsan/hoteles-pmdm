package com.ejemplo.hospedajes.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.ejemplo.hospedajes.data.Usuario
import com.ejemplo.hospedajes.navegacion.Seccion
import com.ejemplo.hospedajes.navegacion.esSeccion
import com.ejemplo.hospedajes.navegacion.seccionesMenu

// UD4, apartado 7.5

@Composable
fun CabeceraMenu(usuario: Usuario?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (usuario?.foto != null) {
            Image(
                painter = painterResource(usuario.foto),
                contentDescription = "Foto de perfil",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
            )
        } else {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(64.dp)
            )
        }
        Spacer(Modifier.width(16.dp))
        Column {
            Text(
                text = usuario?.nombre ?: "Invitado",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = usuario?.email ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun ContenidoMenuLateral(
    usuario: Usuario?,
    destino: NavDestination?,
    onSeccionClick: (Seccion) -> Unit
) {
    ModalDrawerSheet {
        CabeceraMenu(usuario)
        Spacer(Modifier.height(12.dp))

        seccionesMenu.forEach { seccion ->
            NavigationDrawerItem(
                label = { Text(seccion.titulo) },
                icon = { Icon(seccion.icono, contentDescription = null) },
                selected = destino.esSeccion(seccion),
                onClick = { onSeccionClick(seccion) },
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
            )
        }
    }
}
