package com.example.examenestudianteasignatura.controlador

// Importaciones necesarias
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.vista.ListaAsignaturasa

class ListaAsignaturasControlador(private val vista: ListaAsignaturasa) {

    // Métodos para interactuar con el modelo y actualizar la vista
    fun obtenerNombreEstudiante(): String {
        // Retorna el nombre del estudiante seleccionado
        return BaseDatosMemoria.estudianteSelecciondo.nombre
    }

    fun obtenerAsignaturas(): List<Asignatura> {
        // Retorna la lista de asignaturas del estudiante seleccionado
        return BaseDatosMemoria.estudianteSelecciondo.asignaturas
    }

    fun anadirMateria() {
        // Lógica para añadir una materia
        // ...
        vista.actualizarLista()
    }

    // Otros métodos relacionados con la lógica de negocios
    // ...
}
