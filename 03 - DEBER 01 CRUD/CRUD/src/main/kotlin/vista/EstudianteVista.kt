package controlador

import modelo.dao.AsignaturaDAO
import modelo.dao.EstudianteDAO
import modelo.entidades.Asignatura
import modelo.entidades.Estudiante
import kotlinx.datetime.LocalDate

class EstudianteVista {
    init {
        mostrarEstudianteVista()
    }
    private fun mostrarEstudianteVista(){
        println("Menú Estudiante:")
        println("1. Crear Estudiante")
        println("2. Leer Estudiantes")
        println("3. Leer estudiante por cédula")
        println("4. Actualizar Estudiante")
        println("5. Borrar Estudiante")
        println("6. Volver al menú principal")
        println("Por favor, ingrese el número de la opción deseada:")
        val opcion = readln().toInt()



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
                MenuPrincipal()
            }
            else -> {
                println("Opción no válida.")
                mostrarEstudianteVista()
            }
        }
    }

    fun crearEstudiante() {
        print("\nIngrese el número de cédula del estudiante: ")
        val cedulaEstudiante = readLine()?: return

        print("\nIngrese la edad del estudiante: ")
        val edadEstudiante = readLine()?.toInt()?: return

        print("\nIngrese la fecha de inscripción (YYYY-MM-DD: ")
        val fechaInscripcionEstudiante: LocalDate
        try {
            fechaInscripcionEstudiante = LocalDate.parse(readlnOrNull().toString())
        }catch (e: Exception){
            println("Respuesta inválida!!!")
            return
        }

        print("\nIngrese estado del estudiante activo/inactivo (a/i): ")
        var estadoEstudiante = false
        when (readLine()?.trim()?.lowercase()){
            "a" -> {
                estadoEstudiante =true
            }
            "i" -> {
                estadoEstudiante = false
            }
            else -> {
                println("Respuesta inválida!!!")
                return
            }
        }


        val asignaturasEstudiante: ArrayList<Asignatura> = arrayListOf()
        var bandera = true

        while(bandera){
            print("\n¿Desea agregar una asignatura? (s/n)")
            when (readLine()?.trim()?.lowercase()){
                "s" -> {
                    //Problema aca seguramente
                    val asignaturaNueva = crearAsignatura()
                    if(asignaturaNueva != null){
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

        val nuevoEstudiante = Estudiante(cedulaEstudiante, edadEstudiante, fechaInscripcionEstudiante, estadoEstudiante, asignaturasEstudiante)
        EstudianteDAO.create(nuevoEstudiante)
        print("\nEstudiante registrado!!!")
    }

    fun leerEstudiante(){
        EstudianteDAO.getEstudiantes().forEachIndexed{ index, estudiante ->
            println("Estudiante ${index +1}: $estudiante")
        }
    }

    fun leerEstudiantePorCedula(){
        print("\nIngrese el número de cédula del estudiante: ")
        val cedula = readLine()?: return
        if (EstudianteDAO.readByCedula(cedula)!= null){
            print("\n"+ EstudianteDAO.readByCedula(cedula))
        }
    }

    fun actualizarEstudiante(){
        print("\nIngrese el número de cédula del estudiante que desea actualizar: ")
        val cedula = readLine()?: return
        if(EstudianteDAO.readByCedula(cedula) != null){
            print("Ingrese la edad del estudiante: ")
            val edad = readLine()?.toInt()?: return
            val asignaturasEstudiante: ArrayList<Asignatura> = arrayListOf()
            var bandera = true


            while(bandera){
                print("\n¿Desea agregar una asignatura? (s/n)")
                when (readLine()?.trim()?.lowercase()){
                    "s" -> {
                        print("Ingrese el código de la asignatura a agregar: ")
                        val codigo = readLine()?: return
                        val asignaturaNueva = AsignaturaDAO.readByCodigo(codigo)
                        if(asignaturaNueva != null){
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

            EstudianteDAO.update(cedula,edad,asignaturasEstudiante)
            println("\n Estudiante actualizado correctamente!!!")
        }else{
            println("No existe dicha asignatura en el sistema")
            return
        }

    }

    fun borrarEstudiante(){
        print("\nIngrese el número de cédula del estudiante que desea borrar: ")
        val cedula = readLine()?: return
        if(EstudianteDAO.readByCedula(cedula) != null){
            println("¿Está seguro que desea eliminar el estudiante? (S/N)")
            when(readLine()?.trim()?.lowercase()){
                "s" -> {
                    EstudianteDAO.deleteByCedula(cedula)
                    println("Estudiante eliminado con éxito!!!")
                }
                "n" -> {
                    println("\nOperación cancelada")
                }
                else -> println("\nOperación cancelada")
            }
        }
    }

    fun crearAsignatura(): Asignatura?{
        print("\nIngrese el nombre de la asignatura: ")
        val nombreAsignatura = readLine()?: return null

        print("\nIngrese el codigo de la asignatura: ")
        val codigoAsignatura = readLine()?: return null

        print("\nIngrese el horario de la asignatura: ")
        val horario = readLine()?: return null

        print("\nIngrese el número de créditos de la asignatura: ")
        val creditosAsignatura = readLine()?.toDoubleOrNull()?: return null

        print("\nIngrese el profesor asignado de la asignatura: ")
        val profesorAsignatura = readLine()?: return null

        val nuevaAsignatura = Asignatura(nombreAsignatura,codigoAsignatura,horario,creditosAsignatura,profesorAsignatura)
        AsignaturaDAO.create(nuevaAsignatura)
        print("\nAsignatura registrada!!!")
        return nuevaAsignatura
    }
}