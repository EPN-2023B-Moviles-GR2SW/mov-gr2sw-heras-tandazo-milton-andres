package vista
import controlador.MenuPrincipalControlador
import modelo.dao.EstudianteDAO

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
                MenuPrincipalControlador.mostrarHorariosEstudiantesAsignaturas()
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
}