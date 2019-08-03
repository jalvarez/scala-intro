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

Otra forma de escribir esto es usar con el concepto funcional de **currying**:
```scala mdoc
val segundoMultiplicador = (multiplicador _).curried

val cuatriplicador = segundoMultiplicador(4)

cuatriplicador(11)
```

... Intentar simplicar el concepto de currying ...
https://docs.scala-lang.org/tour/currying.html