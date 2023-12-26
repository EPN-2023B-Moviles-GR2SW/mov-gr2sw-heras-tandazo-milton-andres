package controlador

import dao.AsignaturaDAO
import entidades.Asignatura

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
            1 -> crearAsignatura()
            2 -> leerAsignaturas()
            3 -> leerAsignaturaPorCodigo()
            4 -> actualizarAsignatura()
            5 -> borrarAsignatura()
            6 -> {
                println("Volviendo al menú principal...")
                MenuPrincipal()
            }
            else -> {
                println("Opción no válida.")
                mostrarAsignaturaVista()
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

    fun leerAsignaturas(){
        AsignaturaDAO.getAsignaturas().forEachIndexed{index, asignatura ->
            println("Asignatura ${index +1}: $asignatura")
        }
    }

    fun leerAsignaturaPorCodigo(){
        print("\nIngrese el código de la asignatura que desea buscar: ")
        val codigo = readLine()?: return
        if (AsignaturaDAO.readByCodigo(codigo) != null){
            print("\n"+ AsignaturaDAO.readByCodigo(codigo))
        }
    }

    fun actualizarAsignatura(){
        print("\nIngrese el código de la asignatura que desea actualizar: ")
        val codigo = readLine()?: return
        if(AsignaturaDAO.readByCodigo(codigo) != null){
            print("Ingrese el número de créditos de la materia: ")
            val creditos = readLine()?.toDoubleOrNull()?: return
            print("Ingrese el nuevo profesor de la materia: ")
            val profesor = readLine()?: return
            AsignaturaDAO.update(codigo,creditos,profesor)
            println("\n Asignatura actualizada correctamente!!!")
        }else{
            println("No existe dicha asignatura en el sistema")
            return
        }

    }

    fun borrarAsignatura(){
        print("\nIngrese el código de la asignatura que desea borrar: ")
        val codigo = readLine()?: return
        if(AsignaturaDAO.readByCodigo(codigo) != null){
            println("¿Está seguro que desea eliminar la materia? (S/N)")
            when(readLine()?.trim()?.lowercase()){
                "s" -> {
                    AsignaturaDAO.deleteByCodigo(codigo)
                    println("Materia eliminada con éxito!!!")
                }
                "n" -> {
                    println("\nOperación cancelada")
                }
                else -> println("\nOperación cancelada")
            }
        }
    }
}