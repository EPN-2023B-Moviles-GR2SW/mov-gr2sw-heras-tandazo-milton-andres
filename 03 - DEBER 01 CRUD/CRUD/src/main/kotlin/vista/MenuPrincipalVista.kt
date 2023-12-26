package controlador
import modelo.dao.EstudianteDAO
import vista.AsignaturaVista
import vista.EstudianteVista

class MenuPrincipalVista {
    init {
        mostrarMenuPrincipal()
    }

    private fun mostrarMenuPrincipal(){
        println("¡Bienvenido!")
        println("Menú:")
        println("1. Mostrar horarios estudiantes-asignaturas")
        println("2. Opciones Estudiante")
        println("3. Opciones Asignaturas")
        println("4. Finalizar")

        // Lógica para leer la opción del usuario
        println("Por favor, ingrese el número de la opción deseada:")
        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> {
                mostrarHorarios()
                mostrarMenuPrincipal()
            }
            2 -> EstudianteVista()
            3 -> AsignaturaVista()
            4 -> println("Gracias por utilizar el programa!")
            else -> {
                println("Opción no válida.")
                mostrarMenuPrincipal()
            }
        }
    }



    fun mostrarHorarios(){
        EstudianteDAO.getEstudiantes().forEachIndexed{ index, estudiante ->
            println("Estudiante ${index + 1}: $estudiante")
        }
    }
}