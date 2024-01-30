package com.example.examenestudianteasignatura.controlador

import ESQLiteHelperEstudiante
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.modelo.Estudiante
import com.example.examenestudianteasignatura.vista.EditarEstudiante
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class EditarEstudianteControlador(private val vista: EditarEstudiante) {
    private val dbHelper = ESQLiteHelperEstudiante(vista)

    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarDatosEstudiante(cedula: String) {
        val estudiante = dbHelper.obtenerEstudiante(cedula)
        estudiante?.let {
            vista.findViewById<EditText>(R.id.inputEditarCedula).setText(it.cedula)
            vista.findViewById<EditText>(R.id.inputEditarNombre).setText(it.nombre)
            vista.findViewById<EditText>(R.id.inputEditarEdad).setText(it.edad.toString())
            vista.findViewById<EditText>(R.id.inputEditarEstado).setText(if (it.activo) "Verdadero" else "Falso")
            vista.findViewById<EditText>(R.id.inputEditarFechaInscripcion).setText(it.getFormattedFechaInscripcion())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editarEstudiante() {
        try {
            val cedula = vista.findViewById<EditText>(R.id.inputEditarCedula).text.toString()
            val nombre = vista.findViewById<EditText>(R.id.inputEditarNombre).text.toString()
            val edad = vista.findViewById<EditText>(R.id.inputEditarEdad).text.toString().toInt()
            val estado = vista.findViewById<EditText>(R.id.inputEditarEstado).text.toString()
            val fechaInscripcion = vista.findViewById<EditText>(R.id.inputEditarFechaInscripcion).text.toString()

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val fecha = LocalDate.parse(fechaInscripcion, formatter)
            val estadoBoolean = estado.uppercase() == "VERDADERO"

            val estudianteEditado = Estudiante(
                cedula,
                nombre,
                edad,
                fecha,
                estadoBoolean
            )

            dbHelper.actualizarEstudiante(estudianteEditado)

            // Manejar el resultado de la actualización, por ejemplo, mostrando un mensaje

        } catch (e: DateTimeParseException) {
            // Manejar error de formato de fecha
        }
    }

    // Necesitarás implementar los métodos obtenerEstudiante y actualizarEstudiante en ESQLiteHelperEstudiante
}
