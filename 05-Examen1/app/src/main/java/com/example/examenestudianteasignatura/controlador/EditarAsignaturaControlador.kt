package com.example.examenestudianteasignatura.controlador

import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.modelo.ESQLiteHelperAsignatura
import com.example.examenestudianteasignatura.vista.EditarAsignatura

class EditarAsignaturaControlador(private val vista: EditarAsignatura) {
    private val dbHelper = ESQLiteHelperAsignatura(vista)

    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarDatosAsignatura(codigoAsignatura: String) {
        // Aquí, obtendrías la asignatura de la base de datos en lugar de BaseDatosMemoria
        val asignatura = dbHelper.obtenerAsignatura(codigoAsignatura)

        asignatura?.let {
            vista.findViewById<EditText>(R.id.inputEditarNombreAsignatura).setText(it.nombre)
            vista.findViewById<EditText>(R.id.inputEditarCodigo).setText(it.codigo)
            vista.findViewById<EditText>(R.id.inputEditarHorario).setText(it.horario)
            vista.findViewById<EditText>(R.id.inputEditarCreditos).setText(it.creditos.toString())
            vista.findViewById<EditText>(R.id.inputEditarProfesorACargo).setText(it.profesor)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun editarMateria(codigoOriginal: String) {
        val codigo = vista.findViewById<EditText>(R.id.inputEditarCodigo).text.toString()
        val nombre = vista.findViewById<EditText>(R.id.inputEditarNombreAsignatura).text.toString()
        val horario = vista.findViewById<EditText>(R.id.inputEditarHorario).text.toString()
        val creditos = vista.findViewById<EditText>(R.id.inputEditarCreditos).text.toString().toDouble()
        val profesorACargo = vista.findViewById<EditText>(R.id.inputEditarProfesorACargo).text.toString()

        val materiaEditada = Asignatura(nombre, codigo, horario, creditos, profesorACargo)

        // Actualizar la asignatura en la base de datos
        val resultado = dbHelper.actualizarAsignatura(codigoOriginal, materiaEditada)

        // Aquí puedes manejar el resultado de la actualización (por ejemplo, mostrar un mensaje de confirmación o error)
    }
}
