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
// segundoMultiplicador: Int => Int => Int = scala.Function2$$Lambda$4958/1084648189@622eb1c9

val cuatriplicador = segundoMultiplicador(4)
// cuatriplicador: Int => Int = scala.Function2$$Lambda$4959/851413957@501725a3

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
// miHp: Calculadora = repl.Session$App$Calculadora@ef21383

miHp.marca
// res7: String = "HP"

miHp.suma(20, 22)
// res8: Int = 42
```

### Constructor

El constructor no es un método especial, es el código fuera de los métodos de la clase:

```scala
class CalculadoraColor(marca: String) {
    /* Constructor */
    val color = if (marca == "HP") "negro" else "gris"

    // Método para las instancias
    def suma(a: Int, b: Int): Int = a + b
}

val miCalculadoraColor = new CalculadoraColor("HP")
// miCalculadoraColor: CalculadoraColor = repl.Session$App$CalculadoraColor@7dc08876

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
// miCalculadoraCiencia: CalculadoraCientifica = repl.Session$App$CalculadoraCientifica@6a77defe

miCalculadoraCiencia.suma(5, 37)
// res11: Int = 42

miCalculadoraCiencia.log(64, 2)
// res12: Double = 6.0
```

También se pueden **sobrecargar métodos** con el mismo nombre pero distintos parámetros:

```scala
class OtraCalculadoraCientifica(marca: String) extends CalculadoraCientifica(marca) {
    def log(m: Double): Double = log(m, 10)
}

val otraCalculadoraCiencia = new OtraCalculadoraCientifica("TI")
// otraCalculadoraCiencia: OtraCalculadoraCientifica = repl.Session$App$OtraCalculadoraCientifica@3a4d42e

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
// unCirculo: Circulo = repl.Session$App$Circulo@1d54a1d2

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

...

## Tipos

...
