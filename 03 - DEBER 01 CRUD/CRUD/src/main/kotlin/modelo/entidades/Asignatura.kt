package modelo.entidades

import kotlinx.serialization.Serializable

@Serializable
data class Asignatura(
    val nombre: String,
    val codigo: String,
    val horario: String,
    var creditos: Double,
    var profesor: String,
) {

}