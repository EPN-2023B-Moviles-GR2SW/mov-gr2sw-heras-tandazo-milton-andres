package modelo.dao

import modelo.entidades.Asignatura
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class AsignaturaDAO {

    companion object {
        private const val ARCHIVO_ASIGNATURAS = "src/main/kotlin/archivos/asignaturas.json"

        fun getAsignaturas(): MutableList<Asignatura> {
            val archivo = File(ARCHIVO_ASIGNATURAS)
            if (!archivo.exists()) {
                archivo.writeText("[]")
            }
            return Json.decodeFromString<MutableList<Asignatura>>(archivo.readText())
        }

        fun create(materia: Asignatura) {
            val materiasActualizadas = getAsignaturas() + materia
            escribirArchivo(materiasActualizadas)
        }

        fun update(codigo: String, creditos: Double, profesor: String) {
            getAsignaturas().apply {
                find { it.codigo == codigo }?.let { materia ->
                    materia.profesor = profesor
                    materia.creditos = creditos
                }
            }.also { escribirArchivo(it) }
        }

        fun readByCodigo(codigo: String): Asignatura? =
            getAsignaturas().find { it.codigo == codigo }

        fun deleteByCodigo(codigo: String) {
            getAsignaturas().let { materias ->
                readByCodigo(codigo)?.let { materia ->
                    materias.remove(materia)
                    escribirArchivo(materias)

                    // Actualizar la lista de estudiantes
                    val estudiantes = EstudianteDAO.getEstudiantes()
                    estudiantes.forEach { estudiante ->
                        estudiante.asignaturas?.remove(materia)
                    }

                    // Escribir la lista actualizada de estudiantes en el archivo
                    EstudianteDAO.actualizarEstudiantes(estudiantes)
                }
            }
        }


        private fun escribirArchivo(asignaturas: List<Asignatura>) {
            try {
                File(ARCHIVO_ASIGNATURAS).writeText(Json.encodeToString(asignaturas))
            } catch (e: IOException) {
                println("\nError al escribir en el archivo 'asignaturas.json': ${e.message}")
            }
        }
    }
}
