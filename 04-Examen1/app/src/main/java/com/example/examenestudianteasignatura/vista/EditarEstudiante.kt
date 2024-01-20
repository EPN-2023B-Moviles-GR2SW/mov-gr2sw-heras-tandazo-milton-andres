package com.example.examenestudianteasignatura.vista

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.MainActivity
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.BaseDatosMemoria
import com.example.examenestudianteasignatura.controlador.EditarEstudianteControlador
import com.example.examenestudianteasignatura.modelo.Estudiante
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class EditarEstudiante : AppCompatActivity() {
    private lateinit var controlador: EditarEstudianteControlador

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_estudiante)

        controlador = EditarEstudianteControlador(this)

        val nombre = findViewById<EditText>(R.id.inputEditarNombre)
        val cedula = findViewById<EditText>(R.id.inputEditarCedula)
        val edad = findViewById<EditText>(R.id.inputEditarEdad)
        val estado = findViewById<EditText>(R.id.inputEditarEstado)
        val fechaInscripcion = findViewById<EditText>(R.id.inputEditarFechaInscripcion)

        controlador.cargarDatosEstudiante(cedula, nombre, edad, estado, fechaInscripcion)

        val btnEditar = findViewById<Button>(R.id.btnEditarEstudiante)
        btnEditar.setOnClickListener {
            controlador.editarEstudiante(cedula, nombre, edad, estado, fechaInscripcion)
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarEstudiante)
        btnCancelar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // Otros métodos relacionados con la UI
}