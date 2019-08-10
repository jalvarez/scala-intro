# Estructuras de datos básicas

*Scala* proporciona una gran variedad de colecciones o estructuras de datos:

## *Arrays*
Los *arrays* como en otros lenguajes es una estructura que mantiene el orden de los elementos, pueden contener duplicados y son mutables.

```scala mdoc
val arrayNumeros = Array(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)

arrayNumeros(3) = 42

arrayNumeros
```

## *List*
Las listas son similares a los arrays pero inmutables.
```scala mdoc:fail
val listaNumeros = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)

listaNumeros

listaNumeros(3) = 42
```

## *Sets*
Los conjuntos no preservan el orden de los elementos, no permite duplicados y siguen siendo inmutables.
```scala mdoc
val conjuntoNumeros = Set(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)

conjuntoNumeros
```

## *Tuple*
Una tupla es una colección lógica de elementos que pueden ser de distintos tipos:

```scala mdoc
val hostYpuerto = ("localhost", 80)
```

Puede acceder a los elementos de la tupla por posición comenzando en uno.
```scala mdoc
hostYpuerto._1

hostYpuerto._2
```

Las tuplas funcionan muy bien con el *pattern-matching*:
```scala mdoc
hostYpuerto match {
    case (_, puerto) => puerto
}
```

Las tuplas de dos elementos también se pueden escribir con `->`:
```scala mdoc
66 -> "Ruta"
```

## *Maps*

Los mapas o diccionarios permite almacenar elementos para recuperarlos usando un índice:
```scala mdoc
val numeroRomano = Map(1 -> "I",
                       2 -> "II",
                       3 -> "III",
                       4 -> "IV",
                       5 -> "5")

numeroRomano(4)
```
## *Option*

*Option* es un contenedor para un valor opcional que puede estar vacío.
```scala mdoc
val conValor: Option[Int] = Some(1)

val sinValor = None

val desdeNulo = Option(null)
```

Como en otros casos se puede utilizar *pattern-matching*:
```scala mdoc
conValor match {
    case Some(v) => "Tiene valor %s".format(v)
    case None => "Sin valor"
}
```

También dispone de métodos para comprobar el contenido:
```scala mdoc
conValor.isDefined

sinValor.isEmpty
```

Así como dos métodos muy interesantes como `orElse` o `getOrElse`:
```scala mdoc
sinValor.orElse(conValor)

sinValor.getOrElse(42)
```

# Combinadores funcionales

## ¿Qué es un combinador?
Según la definición formal en lógica combinatoria es una *función o definición sin variables libres*. Quizás una definición más práctica fue dada por [John Hughes](https://en.wikipedia.org/wiki/John_Hughes_(computer_scientist)) del un combinador como una función que construye framgentos de código a partir de otros fragmentos.

Vamos a ver alguno combinadores incluídos en *scala*:

## *map*
Evalúa una función para cada elemento de una colección:
```scala mdoc
def doblador(x: Int) = x * 2

val numeros = List(1, 2, 3, 4, 5)

numeros.map(doblador)
```

## *filter*
Filtra los elementos de una colección según una función predicado (que devuelve un valor booleano):

```scala mdoc
def esPar(x: Int): Boolean = x % 2 == 0
val numerosPares = numeros.filter(esPar)

numerosPares
```

## *find*
Devuelve el primer elemento (opcional) de la colección que cumple el predicado:
```scala mdoc
val primeroMayorQue3 = numeros.find((x: Int) => x > 2)

primeroMayorQue3
```

## *partition*
Divide una colección en dos siguiendo un predicado:
```scala mdoc
val (numPares, numImpares) = numeros.partition(esPar)

numPares

numImpares
```

## *zip*
Combina como producto cartesiano el contenido de dos colecciones en una:
```scala mdoc(
val caracteres = List('a', 'b', 'c', 'd', 'e')

caracteres.zip(numeros)
```

## *dropWhile*
*Dropa* elementos de la colección mientras se cumpla el predicado:
```scala mdoc
val mayoresDe3 = numeros.dropWhile(_ <= 3)

mayoresDe3
```

## *foldLeft*
*Pliega* una colección sobre un elemento que actúa como acumulador:

```scala mdoc
val sumaNumeros = numeros.foldLeft(0)((acumulado: Int, elemento: Int) => acumulado + elemento) 

sumaNumeros
```

Visualmente:
```scala mdoc
numeros.foldLeft(0)((acumulado: Int, elemento: Int) => {
    println("acumulado: %d, elemento: %d".format(acumulado, elemento))
    acumulado + elemento
})
```

## *foldRight*
Equivalente a *foldLeft* pero aplicando la función por la derecha:
```scala mdoc
numeros.foldRight(0)((acumulado: Int, elemento: Int) => {
    println("acumulado: %d, elemento: %d".format(acumulado, elemento))
    acumulado - elemento
})
```

## *flatten*
*Aplana* un nivel en una colección de colecciones:
```scala mdoc
val listaDeListas = List(List(1, 2), List(3, 4))
listaDeListas.flatten
```

## *flatMap*
Combina un el *mapping* con el *flatting*:
```scala mdoc
listaDeListas.flatMap(l => l.map(_ * 2))
```

## *foreach*
Aplica una sentencia a cada elemento de la colección y no devuelve nada:
```scala mdoc
numeros.foreach { x =>
    println(x)
}
```