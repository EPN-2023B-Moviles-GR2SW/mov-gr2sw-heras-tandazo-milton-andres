package controlador
import modelo.dao.AsignaturaDAO
import modelo.entidades.Asignatura
object AsignaturaControlador {
    fun crearAsignatura(
        nombreAsignatura: String,
        codigoAsignatura: String,
        horario: String,
        creditosAsignatura: Double,
        profesorAsignatura: String
    ): Asignatura? {
        // Lógica para crear una asignatura
        val nuevaAsignatura = Asignatura(nombreAsignatura, codigoAsignatura, horario, creditosAsignatura, profesorAsignatura)
        AsignaturaDAO.create(nuevaAsignatura)
        return nuevaAsignatura
    }

    fun leerAsignaturas(): List<Asignatura>{
        return AsignaturaDAO.getAsignaturas()
    }

    fun leerAsignaturaPorCodigo(codigo: String): Asignatura? {
        return AsignaturaDAO.readByCodigo(codigo)
    }

    fun actualizarAsignatura(codigo: String, creditos: Double, profesor: String): Boolean {
        val asignaturaExistente = AsignaturaDAO.readByCodigo(codigo)

        return if (asignaturaExistente != null) {
            AsignaturaDAO.update(codigo, creditos, profesor)
            true
        } else {
            false
        }
    }

    fun borrarAsignatura(codigo: String): Boolean {
        val asignaturaExistente = AsignaturaDAO.readByCodigo(codigo)

        return if (asignaturaExistente != null) {
            AsignaturaDAO.deleteByCodigo(codigo)
            true
        } else {
            false
        }
    }

}