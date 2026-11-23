package com.ejemplo.hospedajes.logica

import com.ejemplo.hospedajes.data.Hotel

data class HospedajeUiState(
    val hotelList: List<Hotel> = emptyList(),
    // selectedHotelId se quita: el id del hotel viaja en la ruta "detalle/{hotelId}" (UD4, 4.6)
    val hotelToEdit: Hotel? = null,
    val hotelToDelete: Hotel? = null,
    val isAddDialogOpen: Boolean = false,
    val userMessage: String? = null
)
