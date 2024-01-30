package com.example.examenestudianteasignatura.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.MainActivity
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.EditarEstudianteControlador

class EditarEstudiante : AppCompatActivity() {
    private lateinit var controlador: EditarEstudianteControlador

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_estudiante)

        controlador = EditarEstudianteControlador(this)

        // Suponemos que recibes el ID del estudiante (su cédula) a través de un Intent
        val cedulaEstudiante = intent.getStringExtra("CEDULA_ESTUDIANTE") ?: return
        controlador.cargarDatosEstudiante(cedulaEstudiante)

        val btnEditar = findViewById<Button>(R.id.btnEditarEstudiante)
        btnEditar.setOnClickListener {
            controlador.editarEstudiante()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarEstudiante)
        btnCancelar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
