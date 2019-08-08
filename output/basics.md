# Conceptos básicos

## Expresiones

Casi cualquier cosa en scala es una expresión que siempre tendrá un tipo:

```scala
40 + 2
// res0: Int = 42
```

## Valores

Al resultado de un expresión le podemos asignar un nombre o convertirlo en un valor:

```scala
val respuesta = 40 + 2
// respuesta: Int = 42
```

Los valores **no pueden ser modificados**, en este caso tendremos que definir una variable:

```scala
var respuestaIndecisa = 40 + 2
// respuestaIndecisa: Int = 42
respuestaIndecisa = 101
respuestaIndecisa
// res2: Int = 101
```

### Inmutabilidad

Como lenguaje funcional *scala* favorece la **inmutabilidad** o el uso preferente de valores sobre variables:

> Prefer vals, immutable objects, and methods without side effects.  Reach for them first. Use vars, mutable objects, and methods with side effects when you have a specific need and justification for them.

> Programming in Scala, by Odersky, Spoon, and Venners

Los beneficios del *código inmutable* pueden ser:
* Mantener el control sobre el estado y evitar valores inexperados.
* Evita problemas en acceso concurrente.
* Las librerías del lenguaje siguen este principio.

## Funciones

Las funciones se definen con la palabra reservada `def` y especificando los tipos de los parámetros y el valor devuelto:

```scala
def sumaUno(a: Int): Int = a + 1
```

El asignar el resultado de aplicar la función a un valor, el compilador deduce el tipo:

```scala
val resultadoSuma = sumaUno(41)
// resultadoSuma: Int = 42
```

### Funciones anónimas

Se pueden crear funciones sin nombre y asignarlas a un valor:

```scala
val doblar = (x: Int) => x * 2
// doblar: Int => Int = <function1>

doblar(21)
// res3: Int = 42
```

### Aplicación parcial

Una función se puede aplicar parcialmente fijando un argumento, como por ejemplo:

```scala
def multiplicador(m: Int, n: Int): Int = m * n

val triplicador = (x: Int) => multiplicador(3, x)
// triplicador: Int => Int = <function1>

triplicador(11)
// res4: Int = 33
```

Otra forma de escribir esto es usando la técnica funcional de **currying**, que consiste en transformar una función de múltiples argumentos en una secuencia de funciones con un solo argumento, lo que facilita la aplicación parcial.

```scala
val segundoMultiplicador = (multiplicador _).curried
// segundoMultiplicador: Int => Int => Int = scala.Function2$$Lambda$6467/1785587894@2abf0343

val cuatriplicador = segundoMultiplicador(4)
// cuatriplicador: Int => Int = scala.Function2$$Lambda$6468/1198995161@62896952

cuatriplicador(11)
// res5: Int = 44
```

### Argumentos de longitud variable

Existe la posibilidad de definir funciones con parámetros repetidos de un mismo tipo:

```scala
def cuentaNumArgumentos(argumentos: Int*): Int = argumentos.size

cuentaNumArgumentos(1, 2, 3, 4, 5)
// res6: Int = 5
```

## Clases

Una clase que puede contener variables, valores y métodos (funciones con acceso al estado de la clase):

```scala
class Calculadora {
    val marca: String = "HP"
    def suma(a: Int, b: Int): Int = a + b
}
```

Para crear objetos de la clase se usa la palabra reservada `new`:

```scala
val miHp = new Calculadora
// miHp: Calculadora = repl.Session$App$Calculadora@764d11c4

miHp.marca
// res7: String = "HP"

miHp.suma(20, 22)
// res8: Int = 42
```

### Constructor

El constructor no es un método especial, al crear una instancia se ejecuta el código fuera de los métodos de la clase. Los parámetros del constructor se especifican junto al nombre de la clase:

```scala
class CalculadoraColor(marca: String) {
    /* Constructor */
    val color = if (marca == "HP") "negro" else "gris"

    // Método para las instancias
    def suma(a: Int, b: Int): Int = a + b
}

val miCalculadoraColor = new CalculadoraColor("HP")
// miCalculadoraColor: CalculadoraColor = repl.Session$App$CalculadoraColor@7b91eb59

miCalculadoraColor.color
// res9: String = "negro"

miCalculadoraColor.suma(12, 30)
// res10: Int = 42
```

Resaltar como se define el color de la clase con una expresión *if-else*.

### Herencia

Para definir una subclase se utiliza la palabra reservada `extends`:

```scala
class CalculadoraCientifica(marca: String) extends CalculadoraColor(marca) {
    def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

val miCalculadoraCiencia = new CalculadoraCientifica("Casio")
// miCalculadoraCiencia: CalculadoraCientifica = repl.Session$App$CalculadoraCientifica@698f3358

miCalculadoraCiencia.suma(5, 37)
// res11: Int = 42

miCalculadoraCiencia.log(64, 2)
// res12: Double = 6.0
```

Una clase solo puede extender de una superclase.

También se pueden **sobrecargar métodos** con el mismo nombre pero distintos parámetros:

```scala
class OtraCalculadoraCientifica(marca: String) extends CalculadoraCientifica(marca) {
    def log(m: Double): Double = log(m, 10)
}

val otraCalculadoraCiencia = new OtraCalculadoraCientifica("TI")
// otraCalculadoraCiencia: OtraCalculadoraCientifica = repl.Session$App$OtraCalculadoraCientifica@14e5cf25

otraCalculadoraCiencia.log(100)
// res13: Double = 2.0
```

### Clases abstractas

Las clases abstractas puede definir métodos sin implementación, que serán especificados en sus subclases.

```scala
abstract class Forma {
    def getArea(): Int
}

class Circulo(radio: Int) extends Forma {
    override def getArea(): Int = radio * radio * 3
}

val unCirculo = new Circulo(2)
// unCirculo: Circulo = repl.Session$App$Circulo@11040481

unCirculo.getArea()
// res14: Int = 12
```

Señalar que las clases abstractas no se pueden instanciar, es decir no se pueden crear objetos de una clase abstracta.

```scala
val forma = new Forma
// error: class Forma is abstract; cannot be instantiated
// val forma = new Forma
//             ^^^^^^^^^
```

## Traits

Un *trait* es una colección de campos y métodos que puedes extender o mezclar con tus clases.

```scala
trait Vehiculo {
    val marca: String
}

class Coche extends Vehiculo {
    val marca = "Seat"
}
```

Una clase puede extender más de un *trait* usando la palabra reservada `with`:

```scala
trait Remolque {
    val cargaMaxima: Double

    def remolcar(carga: Double): Unit = {
        assert(carga < cargaMaxima, "No puedo remolcar esa carga")
    }
}

class Pickup extends Vehiculo with Remolque {
    val marca = "Toyota"
    val cargaMaxima = 3500
}
```

### ¿Cuándo usar una clase abstracta o un *trait*?

Intenta utilizar *trait*s en primer lugar ya que son más versátiles, una clase puede extender varios traits, a diferencia de las clase abstractas.

Por otra parte los *trait* no tiene parámetros constructores, así que en caso de necesitarlos debes optar por una clase abstracta.

## Tipos

En scala todos los valores son objetos (incluyendo valores numéricos y funciones) y dado que scala está basado en clases, todos los valores son instancias de una clase. El siguiente diagrama ilustra esta jerarquía de clases:

![jerarquía de clases](https://docs.scala-lang.org/resources/images/classhierarchy.img_assist_custom.png "Jerarquía de clases en scala")

### Clases vs Tipos

La **clase** de un objeto define como es implementado: su estado interno y como se realizan las operaciones.

El **tipo** de un objeto solo se refiere al interfaz o conjunto de peticiones a las que puede responder un objeto.

Un objeto puede tener varios tipos y objetos de diferentes clases pueden tener el mismo tipo.

## Clases como funciones

Como scala combina la programación orientada a objetos y la programación funcional, esto se refleja en los métodos *apply* de las clases y objetos, que se ejecutan al invocar una objeto como una función:

```scala
class Bar {
    def apply(p: String): String = p + " bar"
}

val b = new Bar
// b: Bar = repl.Session$App$Bar@3f3514d4

b("hola")
// res16: String = "hola bar"
```

## Objetos

Se pueden definir objetos con un única instancia, con la palabra reservada `object`:

```scala
object Contador {
    var cuenta = 0

    def siguiente(): Int = {
        cuenta += 1
        cuenta
    }
}

Contador.cuenta
// res17: Int = 0

Contador.siguiente()
// res18: Int = 1

Contador.cuenta
// res19: Int = 1
```

Cuando un objeto tiene el mismo nombre de una clase, en este caso se denomina objeto acompañante (*object companion*) y en el que se suelen incluir métodos y valores comunes a todos los objetos de una clase:

```scala
class Minion {
    def saludo(): String = "Platano"
}

object Minion {
    val color = "Amarillo"

    def clonar(): Minion = new Minion
}

val kevin = Minion.clonar()
// kevin: Minion = repl.Session$App$Minion@3374ad75

kevin.saludo()
// res20: String = "Platano"
```

### Funciones como objetos

Siguiendo con la dualidad que ofrece scala entre la programación orientada a objetos y la programación funcional, se puede construir funciones como objetos:

```scala
object añadeUno extends Function1[Int, Int] {
    def apply(m: Int): Int = m + 1
}

añadeUno(41)
// res21: Int = 42
```

Donde `Function1[Int, Int]` es un sinónimo de `Int => Int`.

## Paquetes

Puede organizar las clases, objetos y traits en paquetes. En la primer línea de un fichero se especifica el paquete de todos los elementos que contiene con la palabra reservada `package`.

Como los valores y funciones no se pueden definir fuera de una clase u objeto, se suelen usar objetos dentro de los paquetes para organizarlos:

```scala
package gitub.jalvarez.intro-scala

object colores {
    val azul = "blue"
    val yellow = "amarillo"
}
```

## Case class

Las *case class* son un tipo particular de clases que incluyen automáticamente los métodos:
* `toString` para representar la clase como una cadena de texto.
* métodos que implementan la igualdad entre objetos.

```scala
case class Ordenador(marca: String, modelo: String)

val primerMac = Ordenador("Apple", "iMac")
// primerMac: Ordenador = Ordenador("Apple", "iMac")
val segundoMac = Ordenador("Apple", "iMac")
// segundoMac: Ordenador = Ordenador("Apple", "iMac")
val pc = Ordenador("Dell", "MD12312")
// pc: Ordenador = Ordenador("Dell", "MD12312")

primerMac == pc
// res22: Boolean = false
primerMac == segundoMac
// res23: Boolean = true
```

Nótese que para crear los objetos no se fue necesario usar la palabra reservada `new`.
