package com.example.examenestudianteasignatura.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.CrearAsignaturaControlador

class CrearAsignatura : AppCompatActivity() {
    private lateinit var controlador: CrearAsignaturaControlador
    private var cedulaEstudiante: String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_asignatura)

        controlador = CrearAsignaturaControlador(this)
        // Recibir la cédula del estudiante desde el Intent
        cedulaEstudiante = intent.getStringExtra("CEDULA_ESTUDIANTE")
        val btnCrearNuevaAsignatura = findViewById<Button>(R.id.btnCrearNuevaAsignatura)
        btnCrearNuevaAsignatura.setOnClickListener {
            controlador.crearNuevaAsignatura()
            volverAListaAsignaturas()
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearAsignatura)
        btnCancelar.setOnClickListener {
            volverAListaAsignaturas()        }
    }

    private fun volverAListaAsignaturas() {
        val intent = Intent(this, ListaAsignaturasa::class.java)
        intent.putExtra("CEDULA_ESTUDIANTE", cedulaEstudiante)
        startActivity(intent)
    }
    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}