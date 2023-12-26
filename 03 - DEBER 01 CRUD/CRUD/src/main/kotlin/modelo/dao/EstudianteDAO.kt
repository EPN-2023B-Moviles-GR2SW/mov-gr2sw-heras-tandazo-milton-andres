package modelo.dao

import modelo.entidades.Estudiante
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import modelo.entidades.Asignatura
import java.io.File
import java.io.IOException

class EstudianteDAO {

    companion object {
        private const val ARCHIVO_ESTUDIANTES = "src/main/kotlin/archivos/estudiantes.json"

        fun getEstudiantes(): MutableList<Estudiante> {
            val archivo = File(ARCHIVO_ESTUDIANTES)
            if (!archivo.exists()) archivo.writeText("[]")
            return Json.decodeFromString(archivo.readText())
        }

        fun create(estudiante: Estudiante) {
            val estudiantesActualizados = getEstudiantes() + estudiante
            escribirArchivo(estudiantesActualizados)
        }

        fun update(cedula: String, edad: Int, asignaturas: MutableList<Asignatura>) {
            getEstudiantes().apply {
                find { it.cedula == cedula }?.let { est ->
                    est.edad = edad
                    est.asignaturas?.let {
                        //it.clear()
                        it.addAll(asignaturas)
                    }
                }
            }.also { escribirArchivo(it) }
        }

        fun readByCedula(cedula: String): Estudiante? =
            getEstudiantes().find { it.cedula == cedula }

        fun deleteByCedula(cedula: String) {
            getEstudiantes().let { estudiantes ->
                readByCedula(cedula)?.let { estudiante ->
                    estudiantes.remove(estudiante)
                    escribirArchivo(estudiantes)
                }
            }
        }

        //Encapsulamiento
        fun actualizarEstudiantes(estudiantes: List<Estudiante>) {
            escribirArchivo(estudiantes)
        }

        private fun escribirArchivo(estudiantes: List<Estudiante>) {
            try {
                File(ARCHIVO_ESTUDIANTES).writeText(Json.encodeToString(estudiantes))
            } catch (e: IOException) {
                println("\nError al escribir en el archivo 'estudiantes.json': ${e.message}")
            }
        }
    }
}
