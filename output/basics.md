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
// segundoMultiplicador: Int => Int => Int = scala.Function2$$Lambda$4958/1084648189@596aca9c

val cuatriplicador = segundoMultiplicador(4)
// cuatriplicador: Int => Int = scala.Function2$$Lambda$4959/851413957@4ddeb0fd

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

