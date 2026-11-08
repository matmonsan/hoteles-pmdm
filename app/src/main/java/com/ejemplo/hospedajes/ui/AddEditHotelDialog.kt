package com.ejemplo.hospedajes.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ejemplo.hospedajes.data.Hotel
import com.ejemplo.hospedajes.ui.theme.HospedajesTheme

@Composable
fun FormField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    error: String? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = true,
        isError = error != null,
        supportingText = if (error != null) {
            { Text(error) }
        } else {
            null
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun AddEditHotelDialog(
    hotelToEdit: Hotel? = null,
    onDismiss: () -> Unit,
    onConfirm: (name: String, city: String, province: String, phone: String, image: String) -> Unit
) {
    var name by remember { mutableStateOf(hotelToEdit?.name ?: "") }
    var city by remember { mutableStateOf(hotelToEdit?.city ?: "") }
    var province by remember { mutableStateOf(hotelToEdit?.province ?: "") }
    var phone by remember { mutableStateOf(hotelToEdit?.phone ?: "") }
    var image by remember { mutableStateOf(hotelToEdit?.image ?: "") }

    var showErrors by remember { mutableStateOf(false) }

    val nameError = if (name.isBlank()) "El nombre es obligatorio" else null
    val cityError = if (city.isBlank()) "La ciudad es obligatoria" else null
    val provinceError = if (province.isBlank()) "La provincia es obligatoria" else null
    val phoneError = when {
        phone.isBlank() -> null
        phone.any { !it.isDigit() && it != ' ' } -> "Solo se admiten números y espacios"
        phone.count { it.isDigit() } != 9 -> "El teléfono debe tener 9 cifras"
        else -> null
    }

    val isFormValid = nameError == null && cityError == null &&
        provinceError == null && phoneError == null

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(if (hotelToEdit == null) "Nuevo hospedaje" else "Editar hospedaje")
        },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FormField(
                    value = name,
                    onValueChange = { name = it },
                    label = "Nombre *",
                    error = if (showErrors) nameError else null
                )
                FormField(
                    value = city,
                    onValueChange = { city = it },
                    label = "Ciudad *",
                    error = if (showErrors) cityError else null
                )
                FormField(
                    value = province,
                    onValueChange = { province = it },
                    label = "Provincia *",
                    error = if (showErrors) provinceError else null
                )
                FormField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = "Teléfono",
                    error = if (showErrors) phoneError else null
                )
                FormField(
                    value = image,
                    onValueChange = { image = it },
                    label = "URL de la imagen"
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    if (isFormValid) {
                        onConfirm(name.trim(), city.trim(), province.trim(), phone.trim(), image.trim())
                    } else {
                        showErrors = true
                    }
                },
                shape = RectangleShape
            ) {
                Text(if (hotelToEdit == null) "Guardar" else "Actualizar")
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
fun AddEditHotelDialogPreview() {
    HospedajesTheme {
        AddEditHotelDialog(
            onDismiss = {},
            onConfirm = { _, _, _, _, _ -> }
        )
    }
}
