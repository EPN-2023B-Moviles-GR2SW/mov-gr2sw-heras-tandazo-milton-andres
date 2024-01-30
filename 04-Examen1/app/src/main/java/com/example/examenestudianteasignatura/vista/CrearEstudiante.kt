package com.example.examenestudianteasignatura.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.MainActivity
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.CrearEstudianteControlador

class CrearEstudiante : AppCompatActivity() {
    private lateinit var controlador: CrearEstudianteControlador

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_estudiante)

        controlador = CrearEstudianteControlador(this)

        val btnCrearNuevoEstudiante = findViewById<Button>(R.id.btnCrearNuevoEstudiante)
        btnCrearNuevoEstudiante.setOnClickListener{
            controlador.crearNuevoEstudiante()
            irActividad(MainActivity::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearEstudiante)
        btnCancelar.setOnClickListener{
            irActividad(MainActivity::class.java)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}