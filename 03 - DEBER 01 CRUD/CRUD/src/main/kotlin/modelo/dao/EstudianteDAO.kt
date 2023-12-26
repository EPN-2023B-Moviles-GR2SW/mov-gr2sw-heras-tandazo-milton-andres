package modelo.dao
import modelo.entidades.Asignatura
import modelo.entidades.Estudiante
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.IOException

class EstudianteDAO {

    companion object{

        fun getEstudiantes(): MutableList<Estudiante>{
            val archivoEstudiantes = File("src/main/kotlin/archivos/estudiantes.json")
            if(!archivoEstudiantes.exists() || archivoEstudiantes.length() == 0L){
                archivoEstudiantes.writeText("[]")
            }
            val estudiantesJson = File("src/main/kotlin/archivos/estudiantes.json").readText()
            return Json.decodeFromString<MutableList<Estudiante>>(estudiantesJson)
        }

        fun create(estudiante: Estudiante){
            val estudiantesActualizados = getEstudiantes() + estudiante
            escribirArchivo(estudiantesActualizados)
        }

        fun update(cedula: String, edad: Int, asignaturas: MutableList<Asignatura>) {
            val estudiante = readByCedula(cedula)
            if (estudiante != null) {
                val estudiantes = getEstudiantes()
                estudiantes.forEach { est ->
                    if (est.cedula == cedula) {
                        est.edad = edad
                        est.asignaturas?.addAll(asignaturas)
                    }
                }
                escribirArchivo(estudiantes)
            }
        }




        fun readByCedula(cedula: String): Estudiante? {
            val estudianteEncontrado = getEstudiantes().find { it.cedula == cedula }
            return if (estudianteEncontrado != null) {
                estudianteEncontrado
            } else {
                println("\nNo se encontró al estudiante con cédula: $cedula")
                null
            }
        }

        fun deleteByCedula(cedula: String) {
            val estudianteEncontrado = readByCedula(cedula)
            if (estudianteEncontrado != null) {
                val estudiantes = getEstudiantes()
                estudiantes.remove(estudianteEncontrado)
                escribirArchivo(estudiantes)
            }

        }

        fun escribirArchivo(estudiantes: List<Estudiante>) {
            try {
                File("src/main/kotlin/archivos/estudiantes.json").writeText(Json.encodeToString(estudiantes))
            } catch (e: IOException) {
                println("\nError al escribir en el archivo 'estudiantes.json': ${e.message}")
            }
        }
    }
}