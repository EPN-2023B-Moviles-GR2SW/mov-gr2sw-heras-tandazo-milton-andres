package com.example.examenestudianteasignatura.modelo

import java.time.LocalDate

class Estudiante (
    val cedula: String,
    val nombre: String,
    var edad: Int,
    var fechaInscripcion: LocalDate,
    val activo: Boolean,
    var asignaturas: MutableList<Asignatura> = mutableListOf()
) {
    override fun toString(): String {
            return "Cédula : $cedula\nNombre : $nombre\nEdad : $edad\nEstado : $activo\nFechaInscripcion : $fechaInscripcion"
    }

}