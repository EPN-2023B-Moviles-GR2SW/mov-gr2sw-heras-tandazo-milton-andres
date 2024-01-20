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
import com.example.examenestudianteasignatura.modelo.Asignatura
import com.google.android.material.snackbar.Snackbar

class ListaAsignaturas : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    val estudianteSeleccionado = BaseDatosMemoria.estudianteSelecciondo
    var posicionItemSeleccionado = 0
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_asignaturas)

        val nombreProfesor = findViewById<TextView>(R.id.txtNombreEstudiante)
        nombreProfesor.text = estudianteSeleccionado.nombre

        val listView = findViewById<ListView>(R.id.lvMaterias)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1,
            estudianteSeleccionado.asignaturas
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonVerProfesores= findViewById<Button>(R.id.btnVerEstudiantes)
        botonVerProfesores.setOnClickListener {
            irActividad(MainActivity::class.java)
        }

        val botonAnadirListView = findViewById<Button>(R.id.btnCrearAsignatura)
        botonAnadirListView.setOnClickListener {
            anadirMateria(adaptador)
        }
        registerForContextMenu(listView)
    }

    fun anadirMateria(
        adaptador: ArrayAdapter<Asignatura>
    ){
        irActividad(CrearAsignatura::class.java)
        adaptador.notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // Llenamos las opciones del menu
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_asignatura, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        BaseDatosMemoria.asignaturaSeleccionada = estudianteSeleccionado.asignaturas[posicion]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.itmEditarMateria -> {
                irActividad(EditarAsignatura::class.java)
                return true
            }
            R.id.itmEliminarMateria -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(findViewById(R.id.lvMaterias),
            texto, Snackbar.LENGTH_LONG)
        snack.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarMateria()
            }
        )

        builder.setNegativeButton(
            "Cancelar",
            null
        )

        val dialogo = builder.create()
        dialogo.show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun eliminarMateria () {
        val listView = findViewById<ListView>(R.id.lvMaterias)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1,
            estudianteSeleccionado.asignaturas
        )
        listView.adapter = adaptador
        estudianteSeleccionado.asignaturas.remove(BaseDatosMemoria.asignaturaSeleccionada)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}