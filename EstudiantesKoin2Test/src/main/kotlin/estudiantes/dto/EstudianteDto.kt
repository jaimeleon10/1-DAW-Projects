package dev.jaimeleon.estudiantes.dto

import kotlinx.serialization.Serializable

@Serializable
data class EstudianteDto(
    val id: Long,
    val nombre: String,
    val calificacion: Int,
)