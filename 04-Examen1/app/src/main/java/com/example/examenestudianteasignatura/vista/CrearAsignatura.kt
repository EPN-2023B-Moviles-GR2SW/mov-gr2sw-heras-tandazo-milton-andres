package com.example.examenestudianteasignatura.vista

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
import com.example.examenestudianteasignatura.controlador.CrearAsignaturaControlador
import com.example.examenestudianteasignatura.modelo.Asignatura

class CrearAsignatura : AppCompatActivity() {
    private lateinit var controlador: CrearAsignaturaControlador

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_asignatura)

        controlador = CrearAsignaturaControlador(this)

        val btnCrearNuevaAsignatura = findViewById<Button>(R.id.btnCrearNuevaAsignatura)
        btnCrearNuevaAsignatura.setOnClickListener {
            controlador.crearNuevaAsignatura()
            irActividad(ListaAsignaturasa::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarCrearAsignatura)
        btnCancelar.setOnClickListener {
            irActividad(ListaAsignaturasa::class.java)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

}