package com.example.examenestudianteasignatura.controlador

import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.vista.CrearAsignatura
import com.example.examenestudianteasignatura.modelo.ESQLiteHelperAsignatura

class CrearAsignaturaControlador(private val vista: CrearAsignatura) {

    private val dbHelper = ESQLiteHelperAsignatura(vista)

    @RequiresApi(Build.VERSION_CODES.O)
    fun crearNuevaAsignatura() {
        val codigo = vista.findViewById<EditText>(R.id.inputCodigo).text.toString()
        val nombre = vista.findViewById<EditText>(R.id.inputNombreAsignatura).text.toString()
        val horas = vista.findViewById<EditText>(R.id.inputHorario).text.toString()
        val creditos = vista.findViewById<EditText>(R.id.inputCreditos).text.toString().toDouble()
        val profesorACargo = vista.findViewById<EditText>(R.id.inputProfesorACargo).text.toString()

        val nuevaAsignatura = Asignatura(nombre, codigo, horas, creditos, profesorACargo)

        // Insertar la nueva asignatura en la base de datos SQLite
        val id = dbHelper.crearAsignatura(nuevaAsignatura)

        // Aquí puedes manejar el resultado de la inserción (por ejemplo, mostrar un mensaje de confirmación o error)
    }
}
