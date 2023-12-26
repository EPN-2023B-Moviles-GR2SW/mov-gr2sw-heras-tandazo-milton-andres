package dao
import entidades.Asignatura
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class AsignaturaDAO {
    companion object {
        fun getAsignaturas(): ArrayList<Asignatura> {
            val archivoAsignaturas = File("src/main/kotlin/archivos/asignaturas.json")
            if (!archivoAsignaturas.exists() || archivoAsignaturas.length() == 0L) {
                archivoAsignaturas.writeText("[]")
            }
            //src
            val materiasJson = File("src/main/kotlin/archivos/asignaturas.json").readText()
            return Json.decodeFromString<ArrayList<Asignatura>>(materiasJson)
        }

        fun create(materia: Asignatura) {
            val materiasActualizadas = getAsignaturas() + materia
            escribirArchivo(materiasActualizadas)
        }

        fun update(codigo: String, creditos: Double, profesor: String) {
            if (readByCodigo(codigo) != null) {
                val asignaturas = getAsignaturas()
                asignaturas.forEach { materia ->
                    if (materia.codigo == codigo) {
                        materia.profesor = profesor
                        materia.creditos = creditos
                    }
                }
                escribirArchivo(asignaturas)
            }
        }

        fun readByCodigo(codigo: String): Asignatura? {
            val asignaturaEncontrada = getAsignaturas().find { it.codigo == codigo }
            return if (asignaturaEncontrada != null) {
                asignaturaEncontrada
            } else {
                println("\nNo se encontró la materia con código: $codigo")
                null
            }
        }

        fun deleteByCodigo(codigo: String) {
            val asignaturaEncontrada = readByCodigo(codigo)
            if (asignaturaEncontrada != null) {
                val materias = getAsignaturas()
                materias.remove(asignaturaEncontrada)
                escribirArchivo(materias)
                val estudiantes = EstudianteDAO.getEstudiantes()
                estudiantes.forEach{profesor ->
                    profesor.asignaturas?.remove(asignaturaEncontrada)
                }
                EstudianteDAO.escribirArchivo(estudiantes)
            }
        }

        private fun escribirArchivo(asignaturas: List<Asignatura>) {
            try {
                File("src/main/kotlin/archivos/asignaturas.json").writeText(Json.encodeToString(asignaturas))
            } catch (e: IOException) {
                println("\nError al escribir en el archivo 'asignaturas.json': ${e.message}")
            }
        }
        }
}