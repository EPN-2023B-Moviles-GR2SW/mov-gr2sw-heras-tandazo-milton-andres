import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.examenestudianteasignatura.modelo.Estudiante

class ESQLiteHelperEstudiante(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 3
        private const val DATABASE_NAME = "EstudianteDB"
        private const val TABLE_ESTUDIANTES = "estudiantes"
        private const val KEY_ID = "cedula"
        private const val KEY_NAME = "nombre"
        private const val KEY_AGE = "edad"
        private const val KEY_INSCRIPTION_DATE = "fechaInscripcion"
        private const val KEY_ACTIVE = "activo"
    // Aquí puedes agregar más campos según tu modelo de Estudiante
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(db: SQLiteDatabase) {
        val createEstudianteTable = ("CREATE TABLE " + TABLE_ESTUDIANTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_AGE + " INTEGER, " + KEY_INSCRIPTION_DATE + " TEXT," + KEY_ACTIVE + " INTEGER" + ")")
        db.execSQL(createEstudianteTable)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //SI TENGO PROBLEMAS AUMENTAR VERSION DB y aplicar onUpgrade
        // Eliminar la tabla existente si existe
        //db.execSQL("DROP TABLE IF EXISTS $TABLE_ESTUDIANTES")

        // Luego, crea la nueva tabla
        //onCreate(db)
    }

    // Aquí puedes agregar tus métodos para insertar, actualizar, borrar y consultar estudiantes
    @RequiresApi(Build.VERSION_CODES.O)
    fun crearEstudiante(estudiante: Estudiante): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_ID, estudiante.cedula)  // Agregar la cédula
            put(KEY_NAME, estudiante.nombre)
            put(KEY_AGE, estudiante.edad)
            put(KEY_INSCRIPTION_DATE, estudiante.getFormattedFechaInscripcion())
            put(KEY_ACTIVE, if (estudiante.activo) 1 else 0)
        }

        val id = db.insert(TABLE_ESTUDIANTES, null, contentValues)
        db.close()
        return id
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    fun obtenerEstudiante(cedula: String): Estudiante? {
        val db = this.readableDatabase
        var estudiante: Estudiante? = null

        val selectQuery = "SELECT * FROM $TABLE_ESTUDIANTES WHERE $KEY_ID = ?"
        db.rawQuery(selectQuery, arrayOf(cedula)).use { cursor ->
            if (cursor.moveToFirst()) {
                val nombre = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                val edad = cursor.getInt(cursor.getColumnIndex(KEY_AGE))
                val fechaInscripcionStr = cursor.getString(cursor.getColumnIndex(KEY_INSCRIPTION_DATE))
                val fechaInscripcion = Estudiante.parseFechaInscripcion(fechaInscripcionStr)
                val activo = cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE)) > 0

                estudiante = Estudiante(cedula, nombre, edad, fechaInscripcion, activo)
            }
        }
        db.close()
        return estudiante
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun actualizarEstudiante(estudianteEditado: Estudiante): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(KEY_NAME, estudianteEditado.nombre)
            put(KEY_AGE, estudianteEditado.edad)
            put(KEY_INSCRIPTION_DATE, estudianteEditado.getFormattedFechaInscripcion())
            put(KEY_ACTIVE, if (estudianteEditado.activo) 1 else 0)
        }

        val filasAfectadas = db.update(TABLE_ESTUDIANTES, contentValues, "$KEY_ID = ?", arrayOf(estudianteEditado.cedula))
        db.close()
        return filasAfectadas
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    fun obtenerTodosLosEstudiantes(): List<Estudiante> {
        val estudiantes = mutableListOf<Estudiante>()
        val db = this.readableDatabase

        val selectQuery = "SELECT * FROM $TABLE_ESTUDIANTES"
        db.rawQuery(selectQuery, null).use { cursor ->
            while (cursor.moveToNext()) {
                val cedula = cursor.getString(cursor.getColumnIndex(KEY_ID))
                val nombre = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                val edad = cursor.getInt(cursor.getColumnIndex(KEY_AGE))
                val fechaInscripcionStr = cursor.getString(cursor.getColumnIndex(KEY_INSCRIPTION_DATE))
                val fechaInscripcion = Estudiante.parseFechaInscripcion(fechaInscripcionStr)
                val activo = cursor.getInt(cursor.getColumnIndex(KEY_ACTIVE)) > 0

                val estudiante = Estudiante(cedula, nombre, edad, fechaInscripcion, activo)
                estudiantes.add(estudiante)
            }
        }
        db.close()
        return estudiantes
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("Range")
    fun obtenerEstudiantePorPosicion(posicion: Int): Estudiante? {
        val estudiantes = obtenerTodosLosEstudiantes()

        return if (estudiantes != null && posicion >= 0 && posicion < estudiantes.size) {
            estudiantes[posicion]
        } else {
            null
        }
    }


    fun eliminarEstudiante(cedula: String): Int {
        val db = this.writableDatabase

        // Eliminar el estudiante de la base de datos y obtener el número de filas afectadas
        val filasAfectadas = db.delete(TABLE_ESTUDIANTES, "$KEY_ID = ?", arrayOf(cedula))
        db.close()
        return filasAfectadas
    }
}
