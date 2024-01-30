package com.example.examenestudianteasignatura.modelo

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Estudiante (
    val cedula: String,
    val nombre: String,
    var edad: Int,
    var fechaInscripcion: LocalDate,
    val activo: Boolean
   // var asignaturas: MutableList<Asignatura> = mutableListOf() //este campo de asignaturas, se manejará a través de la base de datos
) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedFechaInscripcion(): String {
        return fechaInscripcion.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun parseFechaInscripcion(fecha: String): LocalDate {
            return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
    }
    override fun toString(): String {
        return "Cédula : $cedula\nNombre : $nombre\nEdad : $edad\nEstado : $activo\nFechaInscripcion : $fechaInscripcion"
    }

}