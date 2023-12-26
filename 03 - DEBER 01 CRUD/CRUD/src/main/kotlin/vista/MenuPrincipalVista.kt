package vista

import controlador.MenuPrincipalControlador

class MenuPrincipalVista {
    init {
        mostrarMenuPrincipal()
    }

    private fun mostrarMenuPrincipal() {
        println("\n================= Menú Principal =================")
        println("1. Mostrar horarios estudiantes-asignaturas")
        println("2. Opciones Estudiante")
        println("3. Opciones Asignaturas")
        println("4. Finalizar")
        println("=================================================")
        println("Por favor, ingrese el número de la opción deseada:")

        val opcion = leerOpcion()

        when (opcion) {
            1 -> {
                println("Horarios de estudiantes:")
                MenuPrincipalControlador.mostrarHorariosEstudiantesAsignaturas()
                mostrarMenuPrincipal()
            }
            2 -> EstudianteVista()
            3 -> AsignaturaVista()
            4 -> println("Gracias por utilizar el programa!")
            else -> {
                println("Opción no válida. Intente de nuevo.")
                mostrarMenuPrincipal()
            }
        }
    }

    private fun leerOpcion(): Int = readLine()?.toIntOrNull() ?: -1
}
