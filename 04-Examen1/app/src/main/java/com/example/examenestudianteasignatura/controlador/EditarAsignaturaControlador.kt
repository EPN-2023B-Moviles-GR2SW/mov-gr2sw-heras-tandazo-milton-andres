package com.example.examenestudianteasignatura.controlador

import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.example.examenestudianteasignatura.vista.EditarAsignatura

class EditarAsignaturaControlador(private val vista: EditarAsignatura) {
    @RequiresApi(Build.VERSION_CODES.O)
    fun cargarDatosAsignatura(nombre: EditText, codigo: EditText, horario: EditText, creditos: EditText, profesor: EditText) {
        val asignatura = BaseDatosMemoria.asignaturaSeleccionada
        nombre.setText(asignatura.nombre)
        codigo.setText(asignatura.codigo)
        horario.setText(asignatura.horario)
        creditos.setText(asignatura.creditos.toString())
        profesor.setText(asignatura.profesor)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun editarMateria() {
        // Recuperar los datos de la vista
        val codigo = vista.findViewById<EditText>(R.id.inputEditarCodigo).text.toString()
        val nombre = vista.findViewById<EditText>(R.id.inputEditarNombreAsignatura).text.toString()
        val horario = vista.findViewById<EditText>(R.id.inputEditarHorario).text.toString()
        val creditos = vista.findViewById<EditText>(R.id.inputEditarCreditos).text.toString().toDouble()
        val profesorACargo = vista.findViewById<EditText>(R.id.inputEditarProfesorACargo).text.toString()

        val materiaEditada = Asignatura(nombre, codigo, horario, creditos, profesorACargo)

        BaseDatosMemoria.estudianteSeleccionado.asignaturas.forEachIndexed { index, materia ->
            if (materia.codigo == BaseDatosMemoria.asignaturaSeleccionada.codigo) {
                BaseDatosMemoria.estudianteSeleccionado.asignaturas[index] = materiaEditada
            }
        }
    }

}
