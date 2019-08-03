# Conceptos básicos

## Expresiones

Casi cualquier cosa en scala es una expresión que siempre tendrá un tipo:
```scala mdoc
40 + 2
```

## Valores

Al resultado de un expresión le podemos asignar un nombre o convertirlo en un valor:
```scala mdoc
val respuesta = 40 + 2
```

Los valores **no pueden ser modificados**, en este caso tendremos que definir una variable:
```scala mdoc
var respuestaIndecisa = 40 + 2
respuestaIndecisa = 101
respuestaIndecisa
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

```scala mdoc
def sumaUno(a: Int): Int = a + 1
```

El asignar el resultado de aplicar la función a un valor, el compilador deduce el tipo:
```scala mdoc
val resultadoSuma = sumaUno(41)
```

### Funciones anónimas
Se pueden crear funciones sin nombre y asignarlas a un valor:
```scala mdoc
val doblar = (x: Int) => x * 2

doblar(21)
```

### Aplicación parcial
Una función se puede aplicar parcialmente fijando un argumento, como por ejemplo:
```scala mdoc
def multiplicador(m: Int, n: Int): Int = m * n

val triplicador = (x: Int) => multiplicador(3, x)

triplicador(11)
```

Otra forma de escribir esto es usando la técnica funcional de **currying**, que consiste en transformar una función de múltiples argumentos en una secuencia de funciones con un solo argumento, lo que facilita la aplicación parcial.

```scala mdoc
val segundoMultiplicador = (multiplicador _).curried

val cuatriplicador = segundoMultiplicador(4)

cuatriplicador(11)
```

### Argumentos de longitud variable

Existe la posibilidad de definir funciones con parámetros repetidos de un mismo tipo:

```scala mdoc
def cuentaNumArgumentos(argumentos: Int*): Int = argumentos.size

cuentaNumArgumentos(1, 2, 3, 4, 5)
```

## Clases

Una clase que puede contener variables, valores y métodos (funciones con acceso al estado de la clase):
```scala mdoc
class Calculadora {
    val marca: String = "HP"
    def suma(a: Int, b: Int): Int = a + b
}
```

Para crear objetos de la clase se usa la palabra reservada `new`:
```scala mdoc
val miHp = new Calculadora

miHp.marca

miHp.suma(20, 22)
```

### Constructor

El constructor no es un método especial, es el código fuera de los métodos de la clase:
```scala mdoc
class CalculadoraColor(marca: String) {
    /* Constructor */
    val color = if (marca == "HP") "negro" else "gris"

    // Método para las instancias
    def suma(a: Int, b: Int): Int = a + b
}

val miCalculadoraColor = new CalculadoraColor("HP")

miCalculadoraColor.color

miCalculadoraColor.suma(12, 30)
```

Resaltar como se define el color de la clase con una expresión *if-else*.

### Herencia
Para definir una subclase se utiliza la palabra reservada `extends`:
```scala mdoc
class CalculadoraCientifica(marca: String) extends CalculadoraColor(marca) {
    def log(m: Double, base: Double) = math.log(m) / math.log(base)
}

val miCalculadoraCiencia = new CalculadoraCientifica("Casio")

miCalculadoraCiencia.suma(5, 37)

miCalculadoraCiencia.log(64, 2)
```

También se pueden **sobrecargar métodos** con el mismo nombre pero distintos parámetros:
```scala mdoc
class OtraCalculadoraCientifica(marca: String) extends CalculadoraCientifica(marca) {
    def log(m: Double): Double = log(m, 10)
}

val otraCalculadoraCiencia = new OtraCalculadoraCientifica("TI")

otraCalculadoraCiencia.log(100)
```

### Clases abstractas
Las clases abstractas puede definir métodos sin implementación, que serán especificados en sus subclases.

```scala mdoc
abstract class Forma {
    def getArea(): Int
}

class Circulo(radio: Int) extends Forma {
    override def getArea(): Int = radio * radio * 3
}

val unCirculo = new Circulo(2)

unCirculo.getArea()
```

Señalar que las clases abstractas no se pueden instanciar, es decir no se pueden crear objetos de una clase abstracta.

```scala mdoc:fail
val forma = new Forma
```

## Traits
...

## Tipos
...