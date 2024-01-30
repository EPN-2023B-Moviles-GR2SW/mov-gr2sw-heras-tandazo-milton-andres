package com.example.examenestudianteasignatura.controlador

import ESQLiteHelperEstudiante
import android.os.Build
import android.util.Log
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.modelo.Estudiante
import com.example.examenestudianteasignatura.vista.CrearEstudiante
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class CrearEstudianteControlador(private val vista: CrearEstudiante) {

    private val dbHelper = ESQLiteHelperEstudiante(vista)

    @RequiresApi(Build.VERSION_CODES.O)
    fun crearNuevoEstudiante() {
        val nombre = vista.findViewById<EditText>(R.id.inputNombre)
        val cedula = vista.findViewById<EditText>(R.id.inputCedula)
        val edad = vista.findViewById<EditText>(R.id.inputEdad)
        val fechaInscripcion = vista.findViewById<EditText>(R.id.inputFechaInscripcion)
        val estado = vista.findViewById<EditText>(R.id.inputEstado)
        val estadoBoolean = estado.text.toString().uppercase() == "VERDADERO"

        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val fecha = LocalDate.parse(fechaInscripcion.text.toString(), formatter)

            val nuevoEstudiante = Estudiante(
                cedula.text.toString(),
                nombre.text.toString(),
                edad.text.toString().toInt(),
                fecha,
                estadoBoolean
            )

            // Aquí se utiliza dbHelper para insertar el nuevo estudiante en la base de datos SQLite
            val id = dbHelper.crearEstudiante(nuevoEstudiante)
            if (id > 0) {
                Snackbar.make(vista.findViewById(android.R.id.content), "Estudiante creado con éxito", Snackbar.LENGTH_LONG).show()
            } else {
                Snackbar.make(vista.findViewById(android.R.id.content), "Error al crear el estudiante", Snackbar.LENGTH_LONG).show()
            }

        } catch (e: DateTimeParseException) {
            Snackbar.make(vista.findViewById(android.R.id.content), "Error al crear el estudiante: formato de fecha incorrecto", Snackbar.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Snackbar.make(vista.findViewById(android.R.id.content), "Error al crear el estudiante: ${e.message}", Snackbar.LENGTH_LONG).show()
        }
        // Dentro de crearNuevoEstudiante
        Log.d("CrearEstudianteControlador", "Nombre: ${nombre.text}, Cédula: ${cedula.text}, Edad: ${edad.text}, Fecha: ${fechaInscripcion.text}, Estado: ${estado.text}")
    }
}

