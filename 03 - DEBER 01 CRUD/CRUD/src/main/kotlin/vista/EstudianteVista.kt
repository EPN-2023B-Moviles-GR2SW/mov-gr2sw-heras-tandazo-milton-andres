package vista

import controlador.EstudianteControlador
import modelo.dao.AsignaturaDAO
import modelo.entidades.Asignatura
import modelo.entidades.Estudiante
import kotlinx.datetime.LocalDate

class EstudianteVista {
    init {
        mostrarEstudianteVista()
    }

    private fun mostrarEstudianteVista() {
        println("Menú Estudiante:")
        println("1. Crear Estudiante")
        println("2. Leer Estudiantes")
        println("3. Leer estudiante por cédula")
        println("4. Actualizar Estudiante")
        println("5. Borrar Estudiante")
        println("6. Volver al menú principal")
        println("Por favor, ingrese el número de la opción deseada:")
        val opcion = readLine()?.toIntOrNull() ?: return

        when (opcion) {
            1 -> {
                crearEstudiante()
                mostrarEstudianteVista()
            }
            2 -> {
                leerEstudiante()
                mostrarEstudianteVista()
            }
            3 -> {
                leerEstudiantePorCedula()
                mostrarEstudianteVista()
            }
            4 -> {
                actualizarEstudiante()
                mostrarEstudianteVista()
            }
            5 -> {
                borrarEstudiante()
                mostrarEstudianteVista()
            }
            6 -> {
                println("Volviendo al menú principal...")
                MenuPrincipalVista()
            }
            else -> {
                println("Opción no válida.")
                mostrarEstudianteVista()
            }
        }
    }

    private fun crearEstudiante() {
        print("\nIngrese el número de cédula del estudiante: ")
        val cedulaEstudiante = readLine() ?: return

        print("\nIngrese la edad del estudiante: ")
        val edadEstudiante = readLine()?.toInt() ?: return

        print("\nIngrese la fecha de inscripción (YYYY-MM-DD): ")
        val fechaInscripcionEstudiante: LocalDate
        try {
            fechaInscripcionEstudiante = LocalDate.parse(readLine().toString())
        } catch (e: Exception) {
            println("Respuesta inválida!!!")
            return
        }

        print("\nIngrese estado del estudiante activo/inactivo (a/i): ")
        val estadoEstudiante = when (readLine()?.trim()?.lowercase()) {
            "a" -> true
            "i" -> false
            else -> {
                println("Respuesta inválida!!!")
                return
            }
        }

        val asignaturasEstudiante: ArrayList<Asignatura> = arrayListOf()
        var bandera = true

        while (bandera) {
            print("\n¿Desea agregar una asignatura? (s/n)")
            when (readLine()?.trim()?.lowercase()) {
                "s" -> {
                    val asignaturaNueva = crearAsignatura()
                    if (asignaturaNueva != null) {
                        asignaturasEstudiante.add(asignaturaNueva)
                    }
                }
                "n" -> {
                    bandera = false
                }
                else -> {
                    println("\nRespuesta inválida!!!")
                }
            }
        }

        val nuevoEstudiante =
            Estudiante(cedulaEstudiante, edadEstudiante, fechaInscripcionEstudiante, estadoEstudiante, asignaturasEstudiante)
        EstudianteControlador.crearEstudiante(nuevoEstudiante)
        print("\nEstudiante registrado!!!")
    }

    private fun leerEstudiante() {
        println("Listado de estudiantes: ")
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
        print("\nIngrese el número de cédula del estudiante que desea buscar: ")
        val cedula = readLine() ?: return
        val estudianteEncontrado = EstudianteControlador.leerEstudiantePorCedula(cedula)

        if (estudianteEncontrado != null) {
            println("Estudiante encontrado: $estudianteEncontrado")
        } else {
            println("No se encontró ningún estudiante con esa cédula.")
        }
    }

    private fun actualizarEstudiante() {
        print("\nIngrese el número de cédula del estudiante que desea actualizar: ")
        val cedula = readLine() ?: return

        print("Ingrese la nueva edad del estudiante: ")
        val edad = readLine()?.toInt() ?: return

        val asignaturasEstudiante: ArrayList<Asignatura> = arrayListOf()
        var bandera = true

        while (bandera) {
            print("\n¿Desea agregar una asignatura? (s/n)")
            when (readLine()?.trim()?.lowercase()) {
                "s" -> {
                    print("Ingrese el código de la asignatura a agregar: ")
                    val codigo = readLine() ?: return
                    val asignaturaNueva = AsignaturaDAO.readByCodigo(codigo)
                    if (asignaturaNueva != null) {
                        asignaturasEstudiante.add(asignaturaNueva)
                    }
                }
                "n" -> {
                    bandera = false
                }
                else -> {
                    println("\nRespuesta inválida!!!")
                }
            }
        }

        val actualizado = EstudianteControlador.actualizarEstudiante(cedula, edad, asignaturasEstudiante)

        if (actualizado) {
            println("Estudiante actualizado correctamente.")
        } else {
            println("No se encontró ningún estudiante con esa cédula.")
        }
    }

    private fun borrarEstudiante() {
        print("\nIngrese el número de cédula del estudiante que desea borrar: ")
        val cedula = readLine() ?: return

        val eliminado = EstudianteControlador.borrarEstudiante(cedula)

        if (eliminado) {
            println("Estudiante eliminado con éxito.")
        } else {
            println("No se encontró ningún estudiante con esa cédula.")
        }
    }

    private fun crearAsignatura(): Asignatura? {
        print("\nIngrese el nombre de la asignatura: ")
        val nombreAsignatura = readLine() ?: return null

        print("\nIngrese el código de la asignatura: ")
        val codigoAsignatura = readLine() ?: return null

        print("\nIngrese el horario de la asignatura: ")
        val horario = readLine() ?: return null

        print("\nIngrese el número de créditos de la asignatura: ")
        val creditosAsignatura = readLine()?.toDoubleOrNull() ?: return null

        print("\nIngrese el profesor asignado de la asignatura: ")
        val profesorAsignatura = readLine() ?: return null

        val nuevaAsignatura =
            Asignatura(nombreAsignatura, codigoAsignatura, horario, creditosAsignatura, profesorAsignatura)
        AsignaturaDAO.create(nuevaAsignatura)
        print("\nAsignatura registrada!!!")
        return nuevaAsignatura
    }
}
