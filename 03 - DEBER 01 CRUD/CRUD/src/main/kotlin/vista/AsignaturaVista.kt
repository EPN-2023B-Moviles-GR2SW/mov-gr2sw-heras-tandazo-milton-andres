package vista

import controlador.AsignaturaControlador

class AsignaturaVista {
    init {
        mostrarAsignaturaVista()
    }


    private fun mostrarAsignaturaVista() {
        println("\n================= Menú Asignaturas =================")
        println("1. Crear Asignatura")
        println("2. Leer asignaturas")
        println("3. Leer asignatura por código")
        println("4. Actualizar asignatura")
        println("5. Borrar asignatura")
        println("6. Volver al menú principal")
        println("====================================================")
        println("Por favor, ingrese el número de la opción deseada:")

        val opcion = leerOpcion()

        when (opcion) {
            1 -> crearAsignatura()
            2 -> leerAsignaturas()
            3 -> leerAsignaturaPorCodigo()
            4 -> actualizarAsignatura()
            5 -> borrarAsignatura()
            6 -> println("Volviendo al menú principal...")
            else -> println("Opción no válida. Intente de nuevo.")
        }

        if (opcion in 1..5) mostrarAsignaturaVista()
        else if (opcion == 6) MenuPrincipalVista()
    }


    private fun leerOpcion(): Int = readLine()?.toIntOrNull() ?: -1

    private fun crearAsignatura() {
        val nombreAsignatura = solicitarInput("\nIngrese el nombre de la asignatura: ")
        val codigoAsignatura = solicitarInput("\nIngrese el codigo de la asignatura: ")
        val horario = solicitarInput("\nIngrese el horario de la asignatura: ")
        val creditosAsignatura = solicitarInput("\nIngrese el número de créditos de la asignatura: ").toDoubleOrNull()
        val profesorAsignatura = solicitarInput("\nIngrese el profesor asignado de la asignatura: ")

        if (creditosAsignatura != null) {
            val nuevaAsignatura = AsignaturaControlador.crearAsignatura(nombreAsignatura, codigoAsignatura, horario, creditosAsignatura, profesorAsignatura)
            if (nuevaAsignatura != null) println("Asignatura registrada: $nuevaAsignatura")
            else println("No se pudo registrar la asignatura.")
        } else {
            println("Entrada inválida para créditos.")
        }
    }


    fun leerAsignaturas(){
        println("Listado de asignaturas: ")
        val asignaturas = AsignaturaControlador.leerAsignaturas()

        if (asignaturas.isNotEmpty()) {
            asignaturas.forEachIndexed { index, asignatura ->
                println("Asignatura ${index + 1}: $asignatura")
            }
        } else {
            println("No hay asignaturas registradas.")
        }
    }

    private fun leerAsignaturaPorCodigo() {
        val codigo = solicitarInput("\nIngrese el código de la asignatura que desea buscar: ")
        val asignaturaEncontrada = AsignaturaControlador.leerAsignaturaPorCodigo(codigo)

        if (asignaturaEncontrada != null) {
            println("Asignatura encontrada: $asignaturaEncontrada")
        } else {
            println("No se encontró ninguna asignatura con ese código.")
        }
    }

    private fun actualizarAsignatura() {
        val codigo = solicitarInput("\nIngrese el código de la asignatura que desea actualizar: ")
        val creditos = solicitarInput("Ingrese el nuevo número de créditos de la materia: ").toDoubleOrNull()
        val profesor = solicitarInput("Ingrese el nuevo profesor de la materia: ")

        if (creditos != null) {
            val actualizado = AsignaturaControlador.actualizarAsignatura(codigo, creditos, profesor)
            if (actualizado) {
                println("Asignatura actualizada correctamente.")
            } else {
                println("No se encontró ninguna asignatura con ese código.")
            }
        } else {
            println("Entrada inválida para créditos.")
        }
    }

    private fun borrarAsignatura() {
        val codigo = solicitarInput("\nIngrese el código de la asignatura que desea borrar: ")
        val eliminado = AsignaturaControlador.borrarAsignatura(codigo)

        if (eliminado) {
            println("Asignatura eliminada con éxito.")
        } else {
            println("No se encontró ninguna asignatura con ese código.")
        }
    }

    private fun solicitarInput(mensaje: String): String {
        print(mensaje)
        return readLine() ?: ""
    }
}