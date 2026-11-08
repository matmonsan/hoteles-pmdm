package com.ejemplo.hospedajes.data

data class Hotel(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val city: String,
    val province: String,
    val phone: String,
    val image: String
)
