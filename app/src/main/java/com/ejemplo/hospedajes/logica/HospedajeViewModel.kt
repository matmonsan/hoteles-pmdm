package com.ejemplo.hospedajes.logica

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ejemplo.hospedajes.data.Hotel
import com.ejemplo.hospedajes.data.HotelRepository

private const val DEFAULT_IMAGE = "https://picsum.photos/id/1043/300/300"

class HospedajeViewModel : ViewModel() {

    var uiState by mutableStateOf(HospedajeUiState())
        private set

    init {
        uiState = uiState.copy(hotelList = HotelRepository.getHotels())
    }

    fun onHotelClicked(hotel: Hotel) {
        uiState = uiState.copy(selectedHotelId = hotel.id)
    }

    fun onBackToList() {
        uiState = uiState.copy(selectedHotelId = null)
    }

    fun onAddHotelClicked() {
        uiState = uiState.copy(isAddDialogOpen = true)
    }

    fun onDismissAddDialog() {
        uiState = uiState.copy(isAddDialogOpen = false)
    }

    fun onInsertHotel(name: String, city: String, province: String, phone: String, image: String) {
        val newHotel = Hotel(
            name = name,
            city = city,
            province = province,
            phone = phone,
            image = image.ifBlank { DEFAULT_IMAGE }
        )
        HotelRepository.addHotel(newHotel)
        uiState = uiState.copy(
            hotelList = HotelRepository.getHotels(),
            isAddDialogOpen = false,
            userMessage = "Hospedaje '${newHotel.name}' añadido correctamente"
        )
    }

    fun onEditHotelClicked(hotel: Hotel) {
        uiState = uiState.copy(hotelToEdit = hotel)
    }

    fun onDismissEditDialog() {
        uiState = uiState.copy(hotelToEdit = null)
    }

    fun onUpdateHotel(id: String, name: String, city: String, province: String, phone: String, image: String) {
        val existingHotel = HotelRepository.getHotelById(id) ?: return
        val updatedHotel = existingHotel.copy(
            name = name,
            city = city,
            province = province,
            phone = phone,
            image = image.ifBlank { DEFAULT_IMAGE }
        )
        HotelRepository.updateHotel(updatedHotel)
        uiState = uiState.copy(
            hotelList = HotelRepository.getHotels(),
            hotelToEdit = null,
            userMessage = "Hospedaje actualizado"
        )
    }

    fun onDeleteHotelClicked(hotel: Hotel) {
        uiState = uiState.copy(hotelToDelete = hotel)
    }

    fun onDismissDeleteDialog() {
        uiState = uiState.copy(hotelToDelete = null)
    }

    fun onConfirmDeleteHotel() {
        val target = uiState.hotelToDelete ?: return
        HotelRepository.deleteHotel(target.id)
        uiState = uiState.copy(
            hotelList = HotelRepository.getHotels(),
            hotelToDelete = null,
            selectedHotelId = null,
            userMessage = "Hospedaje '${target.name}' borrado"
        )
    }

    fun onUserMessageShown() {
        uiState = uiState.copy(userMessage = null)
    }
}
