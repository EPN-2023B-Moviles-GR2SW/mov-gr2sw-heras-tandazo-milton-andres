package com.example.examenestudianteasignatura.controlador

// Importaciones necesarias
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.vista.ListaAsignaturasa

class ListaAsignaturasControlador(private val vista: ListaAsignaturasa) {

    // Métodos para interactuar con el modelo y actualizar la vista
    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerNombreEstudiante(): String {
        // Retorna el nombre del estudiante seleccionado
        return BaseDatosMemoria.estudianteSeleccionado.nombre
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerAsignaturas(): List<Asignatura> {
        // Retorna la lista de asignaturas del estudiante seleccionado
        return BaseDatosMemoria.estudianteSeleccionado.asignaturas
    }

    fun anadirMateria() {
        // Lógica para añadir una materia
        // ...
        vista.actualizarLista()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarMateria(posicion: Int) {
        BaseDatosMemoria.asignaturaSeleccionada = BaseDatosMemoria.estudianteSeleccionado.asignaturas[posicion]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarMateria() {
        BaseDatosMemoria.estudianteSeleccionado.asignaturas.remove(BaseDatosMemoria.asignaturaSeleccionada)
        vista.actualizarLista()
    }
}
