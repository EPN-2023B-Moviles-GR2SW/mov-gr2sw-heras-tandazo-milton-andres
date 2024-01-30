package com.example.examenestudianteasignatura.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.BaseDatosMemoria
import com.example.examenestudianteasignatura.controlador.EditarAsignaturaControlador
import com.example.examenestudianteasignatura.modelo.Asignatura

class EditarAsignatura : AppCompatActivity() {
    private lateinit var controlador: EditarAsignaturaControlador

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_asignatura)

        controlador = EditarAsignaturaControlador(this)


        val codigo = findViewById<EditText>(R.id.inputEditarCodigo)
        val nombre = findViewById<EditText>(R.id.inputEditarNombreAsignatura)
        val horario = findViewById<EditText>(R.id.inputEditarHorario)
        val creditos = findViewById<EditText>(R.id.inputEditarCreditos)
        val profesor = findViewById<EditText>(R.id.inputEditarProfesorACargo)

        controlador.cargarDatosAsignatura(nombre, codigo, horario, creditos, profesor)

        val btnEditarMateria = findViewById<Button>(R.id.btnEditarAsignatura)
        btnEditarMateria.setOnClickListener{
            controlador.editarMateria()
            irActividad(ListaAsignaturasa::class.java)
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarEditarAsignatura)
        btnCancelar.setOnClickListener{
            irActividad(ListaAsignaturasa::class.java)
        }
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}