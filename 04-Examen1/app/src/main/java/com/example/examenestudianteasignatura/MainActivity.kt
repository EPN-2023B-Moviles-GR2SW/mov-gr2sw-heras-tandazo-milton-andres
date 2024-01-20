package com.example.examenestudianteasignatura

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
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.examenestudianteasignatura.controlador.BaseDatosMemoria
import com.example.examenestudianteasignatura.modelo.Estudiante
import com.example.examenestudianteasignatura.vista.CrearEstudiante
import com.example.examenestudianteasignatura.vista.EditarEstudiante
import com.example.examenestudianteasignatura.vista.ListaAsignaturasa
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    val arregloEstudiantes = BaseDatosMemoria.arregloEstudiantes
    @RequiresApi(Build.VERSION_CODES.O)
    val arregloMaterias = BaseDatosMemoria.arregloAsignaturasModelo
    var posicionItemSeleccionado = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.lvMainActivity)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1,
            arregloEstudiantes
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btnCrearEstudiante)
        botonAnadirListView.setOnClickListener {
            anadirProfesor(adaptador)
        }
        registerForContextMenu(listView)
    }

    fun anadirProfesor(
        adaptador: ArrayAdapter<Estudiante>
    ){
        irActividad(CrearEstudiante::class.java)
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
        inflater.inflate(R.menu.menu, menu)
        // Obtener el id del ArrayListSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position
        posicionItemSeleccionado = posicion
        BaseDatosMemoria.estudianteSeleccionado = arregloEstudiantes[posicion]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.miEditar -> {
                irActividad(EditarEstudiante::class.java)
                return true
            }
            R.id.miEliminar -> {
                abrirDialogo()
                return true
            }
            R.id.miVerMaterias -> {
                irActividad(ListaAsignaturasa::class.java)
                return true
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
    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog, which ->
                eliminarProfesor()
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
    fun eliminarProfesor () {
        val listView = findViewById<ListView>(R.id.lvMainActivity)
        val adaptador = ArrayAdapter(
            this, // contexto
            android.R.layout.simple_list_item_1,
            arregloEstudiantes
        )
        listView.adapter = adaptador
        arregloEstudiantes.remove(BaseDatosMemoria.estudianteSeleccionado)
        adaptador.notifyDataSetChanged()
    }

    fun irActividad (
        clase: Class <*>
    ) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
