package com.example.examenestudianteasignatura.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.EditarAsignaturaControlador

class EditarAsignatura : AppCompatActivity() {
    private lateinit var controlador: EditarAsignaturaControlador
    private var cedulaEstudiante: String? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_asignatura)

        controlador = EditarAsignaturaControlador(this)

        val codigoAsignatura = intent.getStringExtra("CODIGO_ASIGNATURA") ?: return
        cedulaEstudiante = intent.getStringExtra("CEDULA_ESTUDIANTE")

        controlador.cargarDatosAsignatura(codigoAsignatura)
        Log.d("EditarAsignatura", "Código asignatura: $codigoAsignatura, Cédula estudiante: $cedulaEstudiante")

        val btnEditarMateria = findViewById<Button>(R.id.btnEditarAsignatura)
        btnEditarMateria.setOnClickListener{
            controlador.editarMateria(codigoAsignatura)
            volverAListaAsignaturas()
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarAsignatura)
        btnCancelar.setOnClickListener{
            volverAListaAsignaturas()
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
    private fun volverAListaAsignaturas() {
        val intent = Intent(this, ListaAsignaturasa::class.java)
        intent.putExtra("CEDULA_ESTUDIANTE", cedulaEstudiante)
        startActivity(intent)
    }
}