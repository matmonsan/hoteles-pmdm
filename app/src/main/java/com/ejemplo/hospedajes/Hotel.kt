package com.ejemplo.hospedajes

// Modelo de datos (UD3, apartado 3.2)
data class Hotel(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val city: String,
    val province: String,
    val phone: String,
    val image: String
)
