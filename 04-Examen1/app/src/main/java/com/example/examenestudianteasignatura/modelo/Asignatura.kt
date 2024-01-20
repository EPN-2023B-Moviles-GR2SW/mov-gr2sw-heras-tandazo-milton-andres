package com.example.examenestudianteasignatura.modelo

class Asignatura (
    val nombre: String,
    val codigo: String,
    val horario: String,
    var creditos: Double,
    var profesor: String,
){
    override fun toString(): String {
        return "Código : $codigo\nNombre : $nombre\nCréditos : $creditos\nHorario : $horario\n" +
                "Profesor a cargo : $profesor"
    }
}