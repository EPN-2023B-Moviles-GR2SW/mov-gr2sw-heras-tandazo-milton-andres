package controlador

import modelo.dao.EstudianteDAO
import modelo.entidades.Asignatura
import modelo.entidades.Estudiante

object EstudianteControlador {
    fun crearEstudiante(estudiante: Estudiante) {
        EstudianteDAO.create(estudiante)
    }

    fun leerEstudiantes(): List<Estudiante> {
        return EstudianteDAO.getEstudiantes()
    }

    fun leerEstudiantePorCedula(cedula: String): Estudiante? {
        return EstudianteDAO.readByCedula(cedula)
    }

    fun actualizarEstudiante(cedula: String, nuevaEdad: Int, asignaturas: MutableList<Asignatura>): Boolean {
        val estudiante = EstudianteDAO.readByCedula(cedula)
        return if (estudiante != null) {
            EstudianteDAO.update(cedula, nuevaEdad,asignaturas)
            true
        } else {
            false
        }
    }

    fun borrarEstudiante(cedula: String): Boolean {
        val estudiante = EstudianteDAO.readByCedula(cedula)
        return if (estudiante != null) {
            EstudianteDAO.deleteByCedula(cedula)
            true
        } else {
            false
        }
    }
}
