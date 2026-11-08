package com.ejemplo.hospedajes

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel

// Decide qué pantalla se ve, muestra los diálogos y el Snackbar (UD3, apartado 8.3)
@Composable
fun AppPantallas(
    viewModel: HospedajeViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    // Se busca en la lista actual: si el hotel se edita, aquí llegan los datos nuevos.
    // Si se borra, find devuelve null y se vuelve a mostrar la lista.
    val hotelSeleccionado = uiState.hotelList.find { it.id == uiState.selectedHotelId }

    // Botón Atrás del móvil: desde el detalle, vuelve a la lista (apartado 5.5)
    BackHandler(enabled = hotelSeleccionado != null) {
        viewModel.onBackToList()
    }

    // Mensajes del Snackbar (apartado 8.2)
    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onUserMessageShown()
        }
    }

    // Navegación (apartado 5)
    if (hotelSeleccionado == null) {
        PantallaPrincipal(
            hoteles = uiState.hotelList,
            snackbarHostState = snackbarHostState,
            onHotelClick = { hotel -> viewModel.onHotelClicked(hotel) },
            onAddClick = { viewModel.onAddHotelClicked() }
        )
    } else {
        PantallaDetalle(
            hotel = hotelSeleccionado,
            snackbarHostState = snackbarHostState,
            onVolver = { viewModel.onBackToList() },
            onEditar = { viewModel.onEditHotelClicked(hotelSeleccionado) },
            onBorrar = { viewModel.onDeleteHotelClicked(hotelSeleccionado) }
        )
    }

    // Diálogos (apartado 7): se dibujan encima de la pantalla visible
    uiState.hotelToDelete?.let { hotel ->
        ConfirmDeleteDialog(
            hotel = hotel,
            onDismiss = { viewModel.onDismissDeleteDialog() },
            onConfirm = { viewModel.onConfirmDeleteHotel() }
        )
    }

    if (uiState.isAddDialogOpen) {
        AddEditHotelDialog(
            onDismiss = { viewModel.onDismissAddDialog() },
            onConfirm = { name, city, province, phone, image ->
                viewModel.onInsertHotel(name, city, province, phone, image)
            }
        )
    }

    uiState.hotelToEdit?.let { hotel ->
        AddEditHotelDialog(
            hotelToEdit = hotel,
            onDismiss = { viewModel.onDismissEditDialog() },
            onConfirm = { name, city, province, phone, image ->
                viewModel.onUpdateHotel(hotel.id, name, city, province, phone, image)
            }
        )
    }
}
