package modelo.entidades

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Estudiante(
    val cedula: String,
    var edad: Int,
    var fechaInscripcion: LocalDate,
    val activo: Boolean,
    var asignaturas: MutableList<Asignatura> = mutableListOf()
) {



}