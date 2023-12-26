package controlador

import modelo.dao.EstudianteDAO
import vista.AsignaturaVista
import vista.EstudianteVista

object MenuPrincipalControlador {
    fun mostrarHorariosEstudiantesAsignaturas() {
        EstudianteDAO.getEstudiantes().forEachIndexed { index, estudiante ->
            println("Horarios de estudiantes:")
            println("    Estudiante ${index + 1}: $estudiante")
        }
    }
}
