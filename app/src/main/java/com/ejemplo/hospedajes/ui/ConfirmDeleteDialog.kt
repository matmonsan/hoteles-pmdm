package com.ejemplo.hospedajes.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.ejemplo.hospedajes.data.Hotel
import com.ejemplo.hospedajes.data.HotelRepository
import com.ejemplo.hospedajes.ui.theme.HospedajesTheme

@Composable
fun ConfirmDeleteDialog(
    hotel: Hotel,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Eliminar hotel") },
        text = { Text("¿Seguro que quieres eliminar ${hotel.name}?") },
        confirmButton = {
            TextButton(
                onClick = onConfirm,
                shape = RectangleShape,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text("Eliminar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, shape = RectangleShape) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ConfirmDeleteDialogPreview() {
    HospedajesTheme {
        ConfirmDeleteDialog(
            hotel = HotelRepository.getHotels().first(),
            onDismiss = {},
            onConfirm = {}
        )
    }
}
