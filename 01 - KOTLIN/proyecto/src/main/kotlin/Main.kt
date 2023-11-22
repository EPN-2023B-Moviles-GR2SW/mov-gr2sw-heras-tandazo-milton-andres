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
    //companion object, ya no puedo mandar nulos
    val sumaCuatro = Suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()
    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)


    //llama constructor papa, pi, elevar, e historial de sumas


    //Arreglos
    //Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf<Int>(1,2,3)
    println(arregloEstatico)

    //Cambiar numero de elementos
    //Arreglo Dinamico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(
        1,2,3,4,5,6,7,8,9,10
    )
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //arreglos tienen metodos especiales

    //FOR EACH -> Unit devuelve unit no devuelve nada
    //Iterar un arreglo
    val respuestaForEach: Unit = arregloDinamico
        .forEach{ valorActual: Int -> //antes flecha colocar los parametros que tengamos, despues flecha codigo que queremos
            println("Valor actual: ${valorActual}")
        }
    //it (en ingles eso) significa el elemento iterado, solo sirve cuando se tiene un parametro
    arregloDinamico.forEach{println("Valor actual: ${it}")}


    //Aparte de devolvernos el valor del arreglo
    //Nos dice cual es el indice del valor actual
    arregloEstatico
        .forEachIndexed{indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //Modificar el arreglo (objeto que esta llegando calcular edad y guardar en objeto)
    //modificar objeto mediante uno de los operadores


    //MAP -> Muta el arreglo (cambia al arreglo, lo transforma en otro y crea arreglo)
    //1) Enviemos el nuevo valor de la iteracion
    //2) Nos devuelve es un NUEVO ARREGLO con los valores modificado

    val respuestaMap: List<Double> = arregloDinamico
        .map{valorActual: Int ->
        return@map valorActual.toDouble() + 100.00}

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map{it + 15}

    
    //Filter -> FILTRAR EL ARREGLO
    //por cada iteracion devolver una expresion, devuelve un nuevo arreglo filtrado
    //1) Devolver una expresion (TRUE o FALSE)
    //2) Nuevo arreglo filtrado
    val respuestaFilter: List<Int> = arregloDinamico
        .filter{valorActual: Int ->
            //Expresion Condicion
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
        }

    val respuestaFilterDos= arregloDinamico.filter{
        it <= 5
    }
    println(respuestaFilter)
    println(respuestaFilterDos)




    //Dentro de arreglos

    //Ejm notas, hay alguno menos de 12, tengo que hacer supletorio, todos tienes mas de 12?

    //OR AND
    //OR -> ANY (alguno cumple?)
    //AND -> ALL (Todos cumplen?)
    val respuestaAny: Boolean = arregloDinamico
        .any { valorActual:Int ->
            return@any(valorActual > 5)
        }
    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all(valorActual >5)
        }
    println(respuestaAll) //false


    //Operador de arreglos, acomodar valores
    //REDUCE -> Valor acumulado
    //Valor acumulado = 0 (Siempre 0 en kotlin)
    // [1,2,3,4,5] -> sumeme todos los valores del arreglo
    //valorIteracion1 = valorEmpieza + 1 = 0+1 =1 -> Iteracion 1
    //valorIteracion2 = valorItecacion1 + 2 = 1+2 =3 -> Iteracion 2
    //valorIteracion3 = valorItecacion2 + 3 = 3+3 =6 -> Iteracion 3
    //valorIteracion4 = valorItecacion3 + 4 = 6+4 =10 -> Iteracion 4
    //valorIteracion5 = valorItecacion4 + 5 = 10+5 =15 -> Iteracion 5

    val respuestaReduce: Int = arregloDinamico
        .reduce { //acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
            return@reduce(acumulado + valorActual)
        }
    println(respuestaReduce) //78

    //acumulado + (itemCarrito.cantidad * itemCarrito.precio)
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

    //Nuevo constructor y usar la clase
//atributos val pi = 3.14, poner funciones

//dentro de clase tenemos modificador de clase, dentro de clase para crear metodo
//podemos poner public por defecto, o usar private o protected, en construccion podiamos definir ejm public var algo: String, no es solo para metroso, tambien para propiedades


//Crear constructor y funcion sumar junto al companion object

    constructor(//  cuarto constructor
        uno: Int?, // parametros
        dos: Int? // parametros
    ) : this(  // llamada constructor primario
        if (uno == null) 0 else uno,
        if (dos == null) 0 else uno
    )

    // public por defecto, o usar private o protected
    public fun sumar(): Int {
        val total = numeroUno + numeroDos
        // Suma.agregarHistorial(total)
        agregarHistorial(total)
        return total
    }
    // Atributos y Metodos "Compartidos"
    companion object {
        // entre las instancias
        val pi = 3.14
        fun elevarAlCuadrado(num: Int): Int {
            return num * num
        }
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma:Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}






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