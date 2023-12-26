package vista

import controlador.AsignaturaControlador

class AsignaturaVista {
    init {
        mostrarAsignaturaVista()
    }

    private fun mostrarAsignaturaVista(){
        println("Menú Asignaturas:")
        println("1. Crear Asignatura")
        println("2. Leer asignaturas")
        println("3. Leer asignatura por código")
        println("4. Actualizar asignatura")
        println("5. Borrar asignatura")
        println("6. Volver al menú principal")
        println("Por favor, ingrese el número de la opción deseada:")
        val opcion = readln().toInt()



        when (opcion) {
            1 -> {
                crearAsignatura()
                mostrarAsignaturaVista()
            }
            2 -> {
                leerAsignaturas()
                mostrarAsignaturaVista()
            }
            3 -> {
                leerAsignaturaPorCodigo()
                mostrarAsignaturaVista()
            }
            4 -> {
                actualizarAsignatura()
                mostrarAsignaturaVista()
            }
            5 -> {
                borrarAsignatura()
                mostrarAsignaturaVista()
            }
            6 -> {
                println("Volviendo al menú principal...")
                MenuPrincipalVista()
            }
            else -> {
                println("Opción no válida.")
                mostrarAsignaturaVista()
            }
        }
    }

    fun crearAsignatura(){
        print("\nIngrese el nombre de la asignatura: ")
        val nombreAsignatura = readLine()?: return

        print("\nIngrese el codigo de la asignatura: ")
        val codigoAsignatura = readLine()?: return

        print("\nIngrese el horario de la asignatura: ")
        val horario = readLine()?: return

        print("\nIngrese el número de créditos de la asignatura: ")
        val creditosAsignatura = readLine()?.toDoubleOrNull()?: return

        print("\nIngrese el profesor asignado de la asignatura: ")
        val profesorAsignatura = readLine()?: return

        val nuevaAsignatura = AsignaturaControlador.crearAsignatura(nombreAsignatura, codigoAsignatura,horario,creditosAsignatura,profesorAsignatura)

        if (nuevaAsignatura != null) {
            println("Asignatura registrada: $nuevaAsignatura")
        } else {
            println("No se pudo registrar la asignatura.")
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
        print("\nIngrese el código de la asignatura que desea buscar: ")
        val codigo = readLine() ?: return
        val asignaturaEncontrada = AsignaturaControlador.leerAsignaturaPorCodigo(codigo)

        if (asignaturaEncontrada != null) {
            println("Asignatura encontrada: $asignaturaEncontrada")
        } else {
            println("No se encontró ninguna asignatura con ese código.")
        }
    }

    private fun actualizarAsignatura() {
        print("\nIngrese el código de la asignatura que desea actualizar: ")
        val codigo = readLine() ?: return

        print("Ingrese el nuevo número de créditos de la materia: ")
        val creditos = readLine()?.toDoubleOrNull() ?: return

        print("Ingrese el nuevo profesor de la materia: ")
        val profesor = readLine() ?: return

        val actualizado = AsignaturaControlador.actualizarAsignatura(codigo, creditos, profesor)

        if (actualizado) {
            println("Asignatura actualizada correctamente.")
        } else {
            println("No se encontró ninguna asignatura con ese código.")
        }
    }

    private fun borrarAsignatura() {
        print("\nIngrese el código de la asignatura que desea borrar: ")
        val codigo = readLine() ?: return

        val eliminado = AsignaturaControlador.borrarAsignatura(codigo)

        if (eliminado) {
            println("Asignatura eliminada con éxito.")
        } else {
            println("No se encontró ninguna asignatura con ese código.")
        }
    }
}