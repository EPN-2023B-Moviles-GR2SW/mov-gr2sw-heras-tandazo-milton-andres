package com.example.examenestudianteasignatura.controlador

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.modelo.Estudiante
import java.time.LocalDate

class BaseDatosMemoria {

    @RequiresApi(Build.VERSION_CODES.O)
    companion object {
        val arregloEstudiantes = arrayListOf<Estudiante>()
        val arregloAsignaturasModelo = arrayListOf<Asignatura>()
        @RequiresApi(Build.VERSION_CODES.O)
        var estudianteSeleccionado = Estudiante("00000000", "nombre", 0, LocalDate.of(1999, 1, 1), false,arregloAsignaturasModelo)
        var asignaturaSeleccionada = Asignatura("","", "", 0.0, "")

        init {
            val arregloAsignaturas1 = arrayListOf<Asignatura>()
            val arregloAsignaturas2 = arrayListOf<Asignatura>()

            arregloAsignaturas1.add(Asignatura("Historia", "h1", "Lunes: 7am - 9am", 3.5, "Juan"))
            arregloAsignaturas1.add(Asignatura("Lengua", "l1", "Lunes: 9am - 11am", 4.5, "David"))
            arregloAsignaturas1.add(Asignatura("Cálculo", "c1", "Lunes: 11am - 13pm", 4.5, "Mario"))

            arregloAsignaturas2.add(Asignatura("Historia", "h1", "Lunes: 13pm - 15pm", 3.5, "Juan"))
            arregloAsignaturas2.add(Asignatura("Lengua", "l1", "Lunes: 15pm - 17pm", 4.5, "David"))
            arregloAsignaturas2.add(Asignatura("Cálculo", "c1", "Lunes: 17pm - 19pm", 4.5, "Mario"))

            arregloEstudiantes.add(Estudiante("1753665968", "Juan", 21, LocalDate.of(2024, 1, 1), true, arregloAsignaturas1))
            arregloEstudiantes.add(Estudiante("1784848648", "Carla", 23, LocalDate.of(2023, 1, 1), true, arregloAsignaturas2))
        }
    }
}