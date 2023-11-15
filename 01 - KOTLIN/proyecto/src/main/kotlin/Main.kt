import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")
    //Variables inmutables (val) y mutables (var)
    val inmutable: String = "Adrian";
    //inmutable = "Vicente";

    var mutable: String = "Vicente";
    mutable = "Adrian";

    // val > var
    // Duck typing, no decir una variable que tipo es, ya se sobreentiende, no necesario colocar que tipo de dato es.
    var ejemploVariable = "Adrian Eguez";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim();
    //ejemploVariable = edadEjemplo;

    //Variables primitivas
    val nombreProfesor: String = "Adrian Eguez";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C';
    val mayorEdad: Boolean = true;
    //Clases Java
    val fechaNacimiento: Date = Date();

    //SWITCH
    val estadoCivilWhen = "C"

    when (estadoCivilWhen){
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Si" else "No"

    calcularSueldo(10.00)
    calcularSueldo(10.00, 15.00, 20.000)
    //Si fun de 25 parametros y queremos usar el ultimo?, llenar todos y luego usar cualquier valor?
    //Podemos usar parametros nombrados para acceder a alguno en especifico
    calcularSueldo(10.00, bonoEspecial = 20.00) //Named Parameter
    //Podemos cambiar de orden
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    val sumaUno = Suma (1,1)
    val sumaDos = Suma(null,1)
    val sumaTres = Suma(1,null)

}

abstract class NumerosJava{ //Solo instanciarla para inicializar otras clases
    protected val numeroUno: Int
    private val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){//Bloque del codigo del constructor
        this.numeroUno = uno
        this.numeroDos = dos
        println("Inicializando")
    }
}

//Clase modo KOTLIN
abstract class Numeros ( //Constructor Primario
    //Ejemplo:
    //uno: Int, (Parametro (sin modificador de acceso))
    //private var uno: Int, //Propiedad Publica Clase numeros.uno
    //var uno: Int, //Propiedad de la clase (por defecto es PUBLIC)
    //public var uno:Int,

    //propiedades
    protected val numeroUno: Int,
    protected val numeroDos: Int,
){
    //var cedula: string = "" (public es por defecto)
    //private valorCalculado: Int = 0 (private)
    init{ //bloque constructor primario
        this.numeroUno; this.numeroDos; //this es opcional
        numeroUno;numeroDos; //sin el "this" es lo mismo
    println("Inicializando")
    }
}

//Va a heredar de clase Numeros, y ahora se va a mandar parametros arriba solo se define propiedaddes
class Suma( //Constructor primario Suma
    //Parametros
    unoParametro: Int,
    dosParametro: Int,
): Numeros(unoParametro, dosParametro){//Extendiendo y mandando los parametros (super)
    init {
        this.numeroUno
        this.numeroDos
    }

    //Para que nos permita recibir nosotros primer parametro nulo o segundo
    constructor( //Segundo constructor
        //Parametros
        uno: Int?,
        dos: Int
    ):this(
        if(uno == null) 0 else uno,
        dos
    )
    constructor( //Tercer constructor
        //Parametros
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos == null) 0 else dos,

    )

}

//Nuevo constructor y usar la clase


//void -> unit (no devuelven nada)
fun imprimirNombre(nombre: String):Unit{
    //"Nombre : " + nombre
    println("Nombre: ${nombre}") //template strings
}




fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opcion null -> nullable, aveces nulo aveces double escribir codigo para ambos casos
): Double{
    //Int -> Int? (nullable)
    //String -> String? (nullable)
    //Date -> Date? (nullable)
    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) + bonoEspecial
    }
}