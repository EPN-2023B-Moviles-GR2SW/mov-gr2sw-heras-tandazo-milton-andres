package com.example.examenestudianteasignatura.vista

import android.content.DialogInterface
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
import com.example.examenestudianteasignatura.controlador.BaseDatosMemoria
import com.example.examenestudianteasignatura.controlador.ListaAsignaturasControlador
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.google.android.material.snackbar.Snackbar

class ListaAsignaturasa : AppCompatActivity() {
    private lateinit var controlador: ListaAsignaturasControlador
    private lateinit var adaptador: ArrayAdapter<Asignatura>
    private var posicionItemSeleccionado: Int = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_asignaturas)

        controlador = ListaAsignaturasControlador(this)

        val nombreProfesor = findViewById<TextView>(R.id.txtNombreEstudiante)
        nombreProfesor.text = controlador.obtenerNombreEstudiante()

        val listView = findViewById<ListView>(R.id.lvMaterias)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, controlador.obtenerAsignaturas())
        listView.adapter = adaptador

        val botonVerProfesores = findViewById<Button>(R.id.btnVerEstudiantes)
        botonVerProfesores.setOnClickListener { irActividad(MainActivity::class.java) }

        val botonAnadirListView = findViewById<Button>(R.id.btnCrearAsignatura)
        botonAnadirListView.setOnClickListener { irActividad(CrearAsignatura::class.java) }
        registerForContextMenu(listView)

    }


    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_asignatura, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        posicionItemSeleccionado = info.position
        controlador.seleccionarMateria(posicionItemSeleccionado)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itmEditarMateria -> {
                irActividad(EditarAsignatura::class.java)
                return true
            }
            R.id.itmEliminarMateria -> {
                controlador.seleccionarMateria(posicionItemSeleccionado)
                abrirDialogo()
                return true
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