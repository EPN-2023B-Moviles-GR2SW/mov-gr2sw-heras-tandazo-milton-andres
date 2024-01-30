package com.example.examenestudianteasignatura.modelo

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESQLiteHelperAsignatura(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AsignaturasDB"
        private const val TABLE_ASIGNATURAS = "asignaturas"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_CODIGO = "codigo"
        private const val KEY_HORARIO = "horario"
        private const val KEY_CREDITOS = "creditos"
        private const val KEY_PROFESOR = "profesor"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createAsignaturaTable = ("CREATE TABLE " + TABLE_ASIGNATURAS + "("
                + KEY_CODIGO + " TEXT PRIMARY KEY,"
                + KEY_NOMBRE + " TEXT,"
                + KEY_HORARIO + " TEXT,"
                + KEY_CREDITOS + " REAL,"
                + KEY_PROFESOR + " TEXT" + ")")
        db.execSQL(createAsignaturaTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ASIGNATURAS")
        onCreate(db)
    }

    fun crearAsignatura(asignatura: Asignatura): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_CODIGO, asignatura.codigo)
            put(KEY_NOMBRE, asignatura.nombre)
            put(KEY_HORARIO, asignatura.horario)
            put(KEY_CREDITOS, asignatura.creditos)
            put(KEY_PROFESOR, asignatura.profesor)
        }
        val id = db.insert(TABLE_ASIGNATURAS, null, contentValues)
        db.close()
        return id
    }

    @SuppressLint("Range")
    fun obtenerAsignatura(codigoAsignatura: String): Asignatura? {
        val db = this.readableDatabase
        var asignatura: Asignatura? = null

        val selectQuery = "SELECT * FROM $TABLE_ASIGNATURAS WHERE $KEY_CODIGO = ?"
        db.rawQuery(selectQuery, arrayOf(codigoAsignatura)).use { cursor ->
            if (cursor.moveToFirst()) {
                asignatura = Asignatura(
                    cursor.getString(cursor.getColumnIndex(KEY_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(KEY_CODIGO)),
                    cursor.getString(cursor.getColumnIndex(KEY_HORARIO)),
                    cursor.getDouble(cursor.getColumnIndex(KEY_CREDITOS)),
                    cursor.getString(cursor.getColumnIndex(KEY_PROFESOR))
                )
            }
        }
        db.close()
        return asignatura
    }

    fun actualizarAsignatura(codigoOriginal: String, materiaEditada: Asignatura): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_NOMBRE, materiaEditada.nombre)
            put(KEY_CODIGO, materiaEditada.codigo) // Si decides cambiar el código de la asignatura
            put(KEY_HORARIO, materiaEditada.horario)
            put(KEY_CREDITOS, materiaEditada.creditos)
            put(KEY_PROFESOR, materiaEditada.profesor)
        }

        // Actualizar la asignatura en la base de datos y obtener el número de filas afectadas
        val filasAfectadas = db.update(TABLE_ASIGNATURAS, contentValues, "$KEY_CODIGO = ?", arrayOf(codigoOriginal))
        db.close()
        return filasAfectadas
    }

    fun eliminarAsignatura(codigoAsignatura: String): Int {
        val db = this.writableDatabase

        // Eliminar la asignatura de la base de datos y obtener el número de filas afectadas
        val filasAfectadas = db.delete(TABLE_ASIGNATURAS, "$KEY_CODIGO = ?", arrayOf(codigoAsignatura))
        db.close()
        return filasAfectadas
    }

    @SuppressLint("Range")
    fun obtenerAsignaturasPorEstudiante(estudianteId: String): List<Asignatura> {
        val asignaturas = mutableListOf<Asignatura>()
        val db = this.readableDatabase

        val selectQuery = "SELECT * FROM $TABLE_ASIGNATURAS"
        db.rawQuery(selectQuery, null).use { cursor ->
            while (cursor.moveToNext()) {
                val asignatura = Asignatura(
                    cursor.getString(cursor.getColumnIndex(KEY_NOMBRE)),
                    cursor.getString(cursor.getColumnIndex(KEY_CODIGO)),
                    cursor.getString(cursor.getColumnIndex(KEY_HORARIO)),
                    cursor.getDouble(cursor.getColumnIndex(KEY_CREDITOS)),
                    cursor.getString(cursor.getColumnIndex(KEY_PROFESOR))
                )
                asignaturas.add(asignatura)
            }
        }
        db.close()
        return asignaturas
    }


    // Aquí puedes agregar más métodos como obtenerAsignatura, actualizarAsignatura, eliminarAsignatura, etc.

    // ... otros métodos para manejar las asignaturas ...
}
