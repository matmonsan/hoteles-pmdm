package com.ejemplo.hospedajes.navegacion

// UD4, apartados 3.2 y 4.1
object Rutas {
    const val INICIO = "inicio"
    const val MIS_HOTELES = "mis_hoteles"
    const val EXPLORAR = "explorar"
    const val ACERCA_DE = "acerca_de"
    const val DETALLE = "detalle/{hotelId}"

    fun detalle(hotelId: String) = "detalle/$hotelId"
}
