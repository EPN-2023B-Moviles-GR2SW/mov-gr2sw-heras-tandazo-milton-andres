package com.example.examenestudianteasignatura.modelo.entidades

import java.time.LocalDate

class Estudiante (
    val cedula: String,
    var edad: Int,
    var fechaInscripcion: LocalDate,
    val activo: Boolean,
    var asignaturas: MutableList<Asignatura> = mutableListOf()
) {


}