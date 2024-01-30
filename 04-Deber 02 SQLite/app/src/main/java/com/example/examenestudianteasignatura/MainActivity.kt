package com.example.examenestudianteasignatura

import ESQLiteHelperEstudiante
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.examenestudianteasignatura.modelo.Estudiante
import com.example.examenestudianteasignatura.vista.CrearEstudiante
import com.example.examenestudianteasignatura.vista.EditarEstudiante
import com.example.examenestudianteasignatura.vista.ListaAsignaturasa
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: ESQLiteHelperEstudiante
    private lateinit var adaptador: ArrayAdapter<Estudiante>
    private var posicionItemSeleccionado = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = ESQLiteHelperEstudiante(this)
        val listView = findViewById<ListView>(R.id.lvMainActivity)
        cargarEstudiantes()

        val botonAnadirListView = findViewById<Button>(R.id.btnCrearEstudiante)
        botonAnadirListView.setOnClickListener {
            irActividad(CrearEstudiante::class.java)
        }
        registerForContextMenu(listView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun cargarEstudiantes()
    {

        val estudiantes = dbHelper.obtenerTodosLosEstudiantes()
        Log.d("MainActivity", "Número de estudiantes: ${estudiantes.size}")
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, estudiantes)
        findViewById<ListView>(R.id.lvMainActivity).adapter = adaptador
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menú
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        // Obtener la posición del elemento seleccionado en la lista
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val estudianteSeleccionado = dbHelper.obtenerEstudiantePorPosicion(posicionItemSeleccionado)
        return when (item.itemId) {
            R.id.miEditar -> {
                val intent = Intent(this, EditarEstudiante::class.java)
                intent.putExtra("CEDULA_ESTUDIANTE", estudianteSeleccionado?.cedula)
                startActivity(intent)
                true
            }
            R.id.miEliminar -> {
                abrirDialogo(estudianteSeleccionado)
                true
            }
            R.id.miVerMaterias -> {
                val estudianteSeleccionado = dbHelper.obtenerEstudiantePorPosicion(posicionItemSeleccionado)
                estudianteSeleccionado?.let {
                    val intent = Intent(this, ListaAsignaturasa::class.java)
                    intent.putExtra("CEDULA_ESTUDIANTE", it.cedula) // Cambia "ID_ESTUDIANTE" por el nombre real del identificador del estudiante
                    startActivity(intent)
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lvMainActivity),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun abrirDialogo(estudiante: Estudiante?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton("Aceptar") { _, _ ->
            estudiante?.let { eliminarEstudiante(it) }
        }
        builder.setNegativeButton("Cancelar", null)
        val dialogo = builder.create()
        dialogo.show()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarEstudiante(estudiante: Estudiante) {
        dbHelper.eliminarEstudiante(estudiante.cedula)
        cargarEstudiantes() // Actualizar la lista de estudiantes
    }
    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
