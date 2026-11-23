package com.ejemplo.hospedajes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ejemplo.hospedajes.data.Hotel
import com.ejemplo.hospedajes.data.HotelRepository
import com.ejemplo.hospedajes.ui.theme.HospedajesTheme

// Detalle de un hotel. El título y la flecha ← están en la barra superior común
// (UD4, apartado 6), y el botón Atrás del móvil lo gestiona el NavHost.
@Composable
fun PantallaDetalle(
    hotel: Hotel,
    onEditar: () -> Unit,
    onBorrar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = hotel.image,
            contentDescription = "Fotografía de ${hotel.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        )
        Text(hotel.name, style = MaterialTheme.typography.headlineSmall)
        Text("Ciudad: ${hotel.city}")
        Text("Provincia: ${hotel.province}")
        Text("Teléfono: ${hotel.phone}")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
        ) {
            OutlinedButton(onClick = onEditar) {
                Text("Editar")
            }
            Button(
                onClick = onBorrar,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error,
                    contentColor = MaterialTheme.colorScheme.onError
                )
            ) {
                Text("Eliminar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaDetallePreview() {
    HospedajesTheme {
        PantallaDetalle(
            hotel = HotelRepository.getHotels().first(),
            onEditar = {},
            onBorrar = {}
        )
    }
}
