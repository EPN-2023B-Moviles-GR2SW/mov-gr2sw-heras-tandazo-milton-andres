package com.example.examenestudianteasignatura.vista

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.examenestudianteasignatura.MainActivity
import com.example.examenestudianteasignatura.R
import com.example.examenestudianteasignatura.controlador.ListaAsignaturasControlador
import com.example.examenestudianteasignatura.modelo.Asignatura

class ListaAsignaturasa : AppCompatActivity() {
    private lateinit var controlador: ListaAsignaturasControlador
    private lateinit var adaptador: ArrayAdapter<Asignatura>
    private lateinit var listView: ListView

    private var posicionItemSeleccionado: Int = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_asignaturas)
        // Obtener el ID del estudiante desde el Intent
        val estudianteId = intent.getStringExtra("CEDULA_ESTUDIANTE") ?: return

        controlador = ListaAsignaturasControlador(this)

        val nombreProfesor = findViewById<TextView>(R.id.txtNombreEstudiante)
        nombreProfesor.text = controlador.obtenerNombreEstudiante(estudianteId)

        // Cargar las asignaturas del estudiante
        val asignaturas = controlador.obtenerAsignaturas(estudianteId)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, asignaturas)
        listView = findViewById<ListView>(R.id.lvMaterias)
        listView.adapter = adaptador

        val botonVerProfesores = findViewById<Button>(R.id.btnVerEstudiantes)
        botonVerProfesores.setOnClickListener { irActividad(MainActivity::class.java) }

        val botonAnadirListView = findViewById<Button>(R.id.btnCrearAsignatura)
        botonAnadirListView.setOnClickListener {
            val intent = Intent(this, CrearAsignatura::class.java)
            intent.putExtra("CEDULA_ESTUDIANTE", estudianteId)
            startActivity(intent)
        }
        registerForContextMenu(listView)
    }


    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    private fun irActividadEditarAsignatura(codigoAsignatura: String, cedulaEstudiante: String) {
        val intent = Intent(this, EditarAsignatura::class.java)
        intent.putExtra("CODIGO_ASIGNATURA", codigoAsignatura)
        intent.putExtra("CEDULA_ESTUDIANTE", cedulaEstudiante) // Asegúrate de que estudianteId contenga la cédula del estudiante

        startActivity(intent)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val cedulaEstudiante = intent.getStringExtra("CEDULA_ESTUDIANTE") ?: return

        menuInflater.inflate(R.menu.menu_asignatura, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
        controlador.seleccionarMateria(cedulaEstudiante,posicionItemSeleccionado)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.itmEditarMateria -> {
                // Obtener la cédula del estudiante desde el Intent
                val cedulaEstudiante = intent.getStringExtra("CEDULA_ESTUDIANTE") ?: return true
                val asignaturaSeleccionada = controlador.obtenerAsignaturas(cedulaEstudiante)[posicionItemSeleccionado] //ERROR obtenerAsignaturas()
                irActividadEditarAsignatura(asignaturaSeleccionada.codigo, cedulaEstudiante)
                true
            }
            R.id.itmEliminarMateria -> {
                val cedulaEstudiante = intent.getStringExtra("CEDULA_ESTUDIANTE") ?: return true

                controlador.seleccionarMateria(cedulaEstudiante,posicionItemSeleccionado)
                abrirDialogo()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun abrirDialogo() {
        AlertDialog.Builder(this).apply {
            setTitle("Desea eliminar")
            setPositiveButton("Aceptar") { _, _ ->
                controlador.eliminarMateria()
            }
            setNegativeButton("Cancelar", null)
            create().show()
        }
    }
    fun actualizarLista() {
        adaptador.notifyDataSetChanged()
    }
}