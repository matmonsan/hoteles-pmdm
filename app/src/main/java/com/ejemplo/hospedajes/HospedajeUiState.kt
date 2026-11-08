package com.ejemplo.hospedajes

// Estado de la interfaz (UD3, apartados 6.3 y 6.6)
data class HospedajeUiState(
    val hotelList: List<Hotel> = emptyList(),
    val selectedHotelId: String? = null,   // 6.3: null = se ve la lista
    val hotelToEdit: Hotel? = null,        // 7.3: hotel en edición
    val hotelToDelete: Hotel? = null,      // 7.2: hotel pendiente de confirmar borrado
    val isAddDialogOpen: Boolean = false,  // 7.3: diálogo de alta abierto
    val userMessage: String? = null        // 8.2: mensaje para el Snackbar
)
