package entidades

import java.time.LocalDate
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException
@Serializable
class Estudiante (
    val cedula: String,
    val nombre: String,
    val fechaInscripción: LocalDate
) {
    companion object{

    }
}