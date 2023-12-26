package vista

import controlador.EstudianteControlador
import modelo.dao.AsignaturaDAO
import modelo.entidades.Asignatura
import modelo.entidades.Estudiante
import kotlinx.datetime.LocalDate
import modelo.dao.EstudianteDAO

class EstudianteVista {
    init {
        mostrarEstudianteVista()
    }

    private fun mostrarEstudianteVista() {
        println("\n================= Menú Estudiante =================")
        println("1. Crear Estudiante")
        println("2. Leer Estudiantes")
        println("3. Leer estudiante por cédula")
        println("4. Actualizar Estudiante")
        println("5. Borrar Estudiante")
        println("6. Volver al menú principal")
        println("===================================================")
        println("Por favor, ingrese el número de la opción deseada:")

        val opcion = leerOpcion()

        when (opcion) {
            1 -> crearEstudiante()
            2 -> leerEstudiante()
            3 -> leerEstudiantePorCedula()
            4 -> actualizarEstudiante()
            5 -> borrarEstudiante()
            6 -> println("Volviendo al menú principal...")
            else -> println("Opción no válida. Intente de nuevo.")
        }

        if (opcion in 1..5) mostrarEstudianteVista()
        else if (opcion == 6) MenuPrincipalVista()
    }

    private fun leerOpcion(): Int = readLine()?.toIntOrNull() ?: -1

    private fun crearEstudiante() {
        val cedulaEstudiante = solicitarInput("\nIngrese el número de cédula del estudiante: ")
        val edadEstudiante = solicitarInput("\nIngrese la edad del estudiante: ").toIntOrNull() ?: return

        val fechaInscripcionEstudiante = solicitarFecha("\nIngrese la fecha de inscripción (YYYY-MM-DD): ") ?: return

        val estadoEstudiante = solicitarEstadoEstudiante("\nIngrese estado del estudiante activo/inactivo (a/i): ") ?: return

        val asignaturasEstudiante = solicitarAsignaturasEstudiante()

        val nuevoEstudiante = Estudiante(cedulaEstudiante, edadEstudiante, fechaInscripcionEstudiante, estadoEstudiante, asignaturasEstudiante)
        EstudianteControlador.crearEstudiante(nuevoEstudiante)
        println("\nEstudiante registrado!!!")
    }

    private fun leerEstudiante() {
        println("\nListado de estudiantes: ")
        val estudiantes = EstudianteControlador.leerEstudiantes()

        if (estudiantes.isNotEmpty()) {
            estudiantes.forEachIndexed { index, estudiante ->
                println("Estudiante ${index + 1}: $estudiante")
            }
        } else {
            println("No hay estudiantes registrados.")
        }
    }

    private fun leerEstudiantePorCedula() {
        val cedula = solicitarInput("\nIngrese el número de cédula del estudiante que desea buscar: ")
        val estudianteEncontrado = EstudianteControlador.leerEstudiantePorCedula(cedula)

        if (estudianteEncontrado != null) {
            println("Estudiante encontrado: $estudianteEncontrado")
        } else {
            println("No se encontró ningún estudiante con esa cédula.")
        }
    }


    private fun actualizarEstudiante() {
        val cedula = solicitarInput("\nIngrese el número de cédula del estudiante que desea actualizar: ")
        val estudianteExistente = EstudianteDAO.readByCedula(cedula)

        if (estudianteExistente == null) {
            println("No se encontró ningún estudiante con esa cédula.")
            return
        }

        val edad = solicitarInput("Ingrese la nueva edad del estudiante: ").toIntOrNull() ?: return
        val asignaturasEstudiante = actualizarAsignaturasEstudiante(estudianteExistente)

        val actualizado = EstudianteControlador.actualizarEstudiante(cedula, edad, asignaturasEstudiante)
        if (actualizado) {
            println("Estudiante actualizado correctamente.")
        } else {
            println("No se encontró ningún estudiante con esa cédula.")
        }
    }

    private fun borrarEstudiante() {
        val cedula = solicitarInput("\nIngrese el número de cédula del estudiante que desea borrar: ")
        val eliminado = EstudianteControlador.borrarEstudiante(cedula)

        if (eliminado) {
            println("Estudiante eliminado con éxito.")
        } else {
            println("No se encontró ningún estudiante con esa cédula.")
        }
    }


    // Funciones auxiliares
    private fun solicitarInput(mensaje: String): String {
        print(mensaje)
        return readLine() ?: ""
    }

    private fun solicitarFecha(mensaje: String): LocalDate? {
        print(mensaje)
        return try {
            LocalDate.parse(readLine().toString())
        } catch (e: Exception) {
            println("Formato de fecha inválido.")
            null
        }
    }

    private fun solicitarEstadoEstudiante(mensaje: String): Boolean? {
        print(mensaje)
        return when (readLine()?.trim()?.lowercase()) {
            "a" -> true
            "i" -> false
            else -> {
                println("Respuesta inválida.")
                null
            }
        }
    }

    private fun solicitarAsignaturasEstudiante(): MutableList<Asignatura> {
        val asignaturas = mutableListOf<Asignatura>()
        var continuar = true
        while (continuar) {
            println("\n¿Desea agregar una asignatura? (s/n)")
            when (readLine()?.trim()?.lowercase()) {
                "s" -> asignaturas.add(crearAsignatura() ?: continue)
                "n" -> continuar = false
                else -> println("Respuesta inválida.")
            }
        }
        return asignaturas
    }
    private fun crearAsignatura(): Asignatura? {
        val nombreAsignatura = solicitarInput("\nIngrese el nombre de la asignatura: ")
        if (nombreAsignatura.isBlank()) {
            println("Nombre de asignatura no puede estar vacío.")
            return null
        }

        val codigoAsignatura = solicitarInput("\nIngrese el código de la asignatura: ")
        if (codigoAsignatura.isBlank()) {
            println("Código de asignatura no puede estar vacío.")
            return null
        }

        val horario = solicitarInput("\nIngrese el horario de la asignatura: ")
        if (horario.isBlank()) {
            println("Horario no puede estar vacío.")
            return null
        }

        val creditosInput = solicitarInput("\nIngrese el número de créditos de la asignatura: ")
        val creditosAsignatura = creditosInput.toDoubleOrNull()
        if (creditosAsignatura == null || creditosAsignatura <= 0) {
            println("Número de créditos inválido.")
            return null
        }

        val profesorAsignatura = solicitarInput("\nIngrese el profesor asignado de la asignatura: ")
        if (profesorAsignatura.isBlank()) {
            println("Nombre del profesor no puede estar vacío.")
            return null
        }

        val nuevaAsignatura = Asignatura(nombreAsignatura, codigoAsignatura, horario, creditosAsignatura, profesorAsignatura)
        return nuevaAsignatura
    }
    private fun actualizarAsignaturasEstudiante(estudianteExistente: Estudiante): MutableList<Asignatura> {
        val asignaturasEstudiante = estudianteExistente.asignaturas?.toMutableList() ?: mutableListOf()
        var bandera = true

        while (bandera) {
            val respuesta = solicitarInput("\n¿Desea agregar una asignatura? (s/n)").lowercase()
            when (respuesta) {
                "s" -> agregarAsignatura(asignaturasEstudiante)
                "n" -> bandera = false
                else -> println("\nRespuesta inválida!!!")
            }
        }
        return asignaturasEstudiante
    }
    private fun agregarAsignatura(asignaturas: MutableList<Asignatura>) {
        val codigo = solicitarInput("Ingrese el código de la asignatura a agregar: ")
        val asignaturaNueva = AsignaturaDAO.readByCodigo(codigo)

        if (asignaturaNueva != null) {
            if (asignaturas.any { it.codigo == asignaturaNueva.codigo }) {
                println("Asignatura ya registrada en su horario.")
            } else {
                asignaturas.add(asignaturaNueva)
            }
        } else {
            println("No se encontró una asignatura con ese código.")
        }
    }
}



