package com.example.examenestudianteasignatura.controlador

import ESQLiteHelperEstudiante
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.modelo.ESQLiteHelperAsignatura
import com.example.examenestudianteasignatura.vista.ListaAsignaturasa

class ListaAsignaturasControlador(private val vista: ListaAsignaturasa) {
    private val dbHelperAsignatura = ESQLiteHelperAsignatura(vista)
    private val dbHelperEstudiante = ESQLiteHelperEstudiante(vista)
    private var asignaturaSeleccionada: Asignatura? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerNombreEstudiante(cedulaEstudiante: String): String {
        //val cedulaEstudiante = vista.intent.getStringExtra("ID_ESTUDIANTE") ?: return "Nombre no disponible"

        val estudiante = dbHelperEstudiante.obtenerEstudiante(cedulaEstudiante)
        return estudiante?.nombre ?: "Nombre no disponible"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun obtenerAsignaturas(cedulaEstudiante: String): List<Asignatura> {
        // Implementación para obtener las asignaturas del estudiante seleccionado de la base de datos
        return dbHelperAsignatura.obtenerAsignaturasPorEstudiante(cedulaEstudiante)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun seleccionarMateria(cedulaEstudiante: String,posicion: Int) {

        val asignaturas = obtenerAsignaturas(cedulaEstudiante) //ERROR
        asignaturaSeleccionada = if (asignaturas.size > posicion) asignaturas[posicion] else null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarMateria() {
        asignaturaSeleccionada?.let {
            dbHelperAsignatura.eliminarAsignatura(it.codigo)
            vista.actualizarLista()
        }
    }

    // ... Resto de métodos ...
}
