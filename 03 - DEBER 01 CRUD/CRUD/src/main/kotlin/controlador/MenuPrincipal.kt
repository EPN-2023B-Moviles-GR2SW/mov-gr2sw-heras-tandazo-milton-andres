package controlador
import dao.EstudianteDAO
import dao.AsignaturaDAO

class MenuPrincipal {
    init {
        mostrarMenuPrincipal()
    }

    private fun mostrarMenuPrincipal(){
        println("¡Bienvenido!")
        println("Menú:")
        println("1. Mostrar horarios")
        println("2. Opciones Estudiante")
        println("3. Opciones Asignaturas")

        // Lógica para leer la opción del usuario
        println("Por favor, ingrese el número de la opción deseada:")
        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> mostrarHorarios()
            2 -> AsignaturaVista()
            3 -> EstudianteVista()
            else -> println("Opción no válida.")
        }
    }



    fun mostrarHorarios(){
        EstudianteDAO.getEstudiantes().forEachIndexed{ index, estudiante ->
            println("Estudiante ${index + 1}: $estudiante")
        }
    }
}