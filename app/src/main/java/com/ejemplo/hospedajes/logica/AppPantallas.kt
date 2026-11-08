package com.ejemplo.hospedajes.logica

import androidx.activity.compose.BackHandler
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ejemplo.hospedajes.ui.AddEditHotelDialog
import com.ejemplo.hospedajes.ui.ConfirmDeleteDialog
import com.ejemplo.hospedajes.ui.PantallaDetalle
import com.ejemplo.hospedajes.ui.PantallaPrincipal

@Composable
fun AppPantallas(
    viewModel: HospedajeViewModel = viewModel()
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }

    val hotelSeleccionado = uiState.hotelList.find { it.id == uiState.selectedHotelId }

    BackHandler(enabled = hotelSeleccionado != null) {
        viewModel.onBackToList()
    }

    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.onUserMessageShown()
        }
    }

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
