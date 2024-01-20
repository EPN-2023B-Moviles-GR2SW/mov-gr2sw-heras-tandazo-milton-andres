package com.example.examenestudianteasignatura.controlador

import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.modelo.Estudiante
import com.example.examenestudianteasignatura.vista.EditarEstudiante
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class EditarEstudianteControlador(private val vista: EditarEstudiante) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarDatosEstudiante(cedula: EditText, nombre: EditText, edad: EditText, estado: EditText, fechaInscripcion: EditText) {
        val estudiante = BaseDatosMemoria.estudianteSeleccionado
        cedula.setText(estudiante.cedula)
        nombre.setText(estudiante.nombre)
        edad.setText(estudiante.edad.toString())
        estado.setText(if (estudiante.activo) "Verdadero" else "Falso")
        fechaInscripcion.setText(estudiante.fechaInscripcion.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editarEstudiante(cedula: EditText, nombre: EditText, edad: EditText, estado: EditText, fechaInscripcion: EditText) {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val fecha = LocalDate.parse(fechaInscripcion.text.toString(), formatter)
            val estadoBoolean = estado.text.toString().uppercase() == "VERDADERO"

            val estudianteEditado = Estudiante(
                cedula.text.toString(),
                nombre.text.toString(),
                edad.text.toString().toInt(),
                fecha,
                estadoBoolean,
                BaseDatosMemoria.estudianteSeleccionado.asignaturas
            )

            BaseDatosMemoria.arregloEstudiantes.forEachIndexed { index, estudiante ->
                if (estudiante.cedula == BaseDatosMemoria.estudianteSeleccionado.cedula) {
                    BaseDatosMemoria.arregloEstudiantes[index] = estudianteEditado
                }
            }
        } catch (e: DateTimeParseException) {
            // Manejar error, por ejemplo mostrando un Snackbar
        }
    }
}
