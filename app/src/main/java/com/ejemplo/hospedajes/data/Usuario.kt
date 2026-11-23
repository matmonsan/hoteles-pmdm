package com.ejemplo.hospedajes.data

import com.ejemplo.hospedajes.R

// UD4, apartado 7.5: datos del usuario que se muestran en la cabecera del menú lateral

data class Usuario(
    val nombre: String,
    val email: String,
    val foto: Int? = null
)

val usuarioEjemplo = Usuario(
    nombre = "Ana García",
    email = "ana@ejemplo.com",
    foto = R.drawable.foto_perfil
)
