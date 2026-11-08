package com.ejemplo.hospedajes.logica

import com.ejemplo.hospedajes.data.Hotel

data class HospedajeUiState(
    val hotelList: List<Hotel> = emptyList(),
    val selectedHotelId: String? = null,
    val hotelToEdit: Hotel? = null,
    val hotelToDelete: Hotel? = null,
    val isAddDialogOpen: Boolean = false,
    val userMessage: String? = null
)
