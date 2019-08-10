# Estructuras de datos básicas

*Scala* proporciona una gran variedad de colecciones o estructuras de datos:

## *Arrays*

Los *arrays* como en otros lenguajes es una estructura que mantiene el orden de los elementos, pueden contener duplicados y son mutables.

```scala
val arrayNumeros = Array(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
// arrayNumeros: Array[Int] = Array(1, 2, 3, 42, 5, 1, 2, 3, 4, 5)

arrayNumeros(3) = 42

arrayNumeros
// res1: Array[Int] = Array(1, 2, 3, 42, 5, 1, 2, 3, 4, 5)
```

## *List*

Las listas son similares a los arrays pero inmutables.

```scala
val listaNumeros = List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)

listaNumeros

listaNumeros(3) = 42
// error: value update is not a member of List[Int]
// listaNumeros(3) = 42
// ^^^^^^^^^^^^
```

## *Sets*

Los conjuntos no preservan el orden de los elementos, no permite duplicados y siguen siendo inmutables.

```scala
val conjuntoNumeros = Set(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
// conjuntoNumeros: Set[Int] = Set(5, 1, 2, 3, 4)

conjuntoNumeros
// res3: Set[Int] = Set(5, 1, 2, 3, 4)
```

## *Tuple*

Una tupla es una colección lógica de elementos que pueden ser de distintos tipos:

```scala
val hostYpuerto = ("localhost", 80)
// hostYpuerto: (String, Int) = ("localhost", 80)
```

Puede acceder a los elementos de la tupla por posición comenzando en uno.

```scala
hostYpuerto._1
// res4: String = "localhost"

hostYpuerto._2
// res5: Int = 80
```

Las tuplas funcionan muy bien con el *pattern-matching*:

```scala
hostYpuerto match {
    case (_, puerto) => puerto
}
// res6: Int = 80
```

Las tuplas de dos elementos también se pueden escribir con `->`:

```scala
66 -> "Ruta"
// res7: (Int, String) = (66, "Ruta")
```

## *Maps*

Los mapas o diccionarios permite almacenar elementos para recuperarlos usando un índice:

```scala
val numeroRomano = Map(1 -> "I",
                       2 -> "II",
                       3 -> "III",
                       4 -> "IV",
                       5 -> "5")
// numeroRomano: Map[Int, String] = Map(
//   5 -> "5",
//   1 -> "I",
//   2 -> "II",
//   3 -> "III",
//   4 -> "IV"
// )

numeroRomano(4)
// res8: String = "IV"
```

## *Option*

*Option* es un contenedor para un valor opcional que puede estar vacío.

```scala
val conValor: Option[Int] = Some(1)
// conValor: Option[Int] = Some(1)

val sinValor = None
// sinValor: None.type = None

val desdeNulo = Option(null)
// desdeNulo: Option[Null] = None
```

Como en otros casos se puede utilizar *pattern-matching*:

```scala
conValor match {
    case Some(v) => "Tiene valor %s".format(v)
    case None => "Sin valor"
}
// res9: String = "Tiene valor 1"
```

También dispone de métodos para comprobar el contenido:

```scala
conValor.isDefined
// res10: Boolean = true

sinValor.isEmpty
// res11: Boolean = true
```

Así como dos métodos muy interesantes como `orElse` o `getOrElse`:

```scala
sinValor.orElse(conValor)
// res12: Option[Int] = Some(1)

sinValor.getOrElse(42)
// res13: Int = 42
```

# Combinadores funcionales

## ¿Qué es un combinador?

Según la definición formal en lógica combinatoria es una *función o definición sin variables libres*. Quizás una definición más práctica fue dada por [John Hughes](https://en.wikipedia.org/wiki/John_Hughes_(computer_scientist)) del un combinador como una función que construye framgentos de código a partir de otros fragmentos.

Vamos a ver alguno combinadores incluídos en *scala*:

## *map*

Evalúa una función para cada elemento de una colección:

```scala
def doblador(x: Int) = x * 2

val numeros = List(1, 2, 3, 4, 5)
// numeros: List[Int] = List(1, 2, 3, 4, 5)

numeros.map(doblador)
// res14: List[Int] = List(2, 4, 6, 8, 10)
```

## *filter*

Filtra los elementos de una colección según una función predicado (que devuelve un valor booleano):

```scala
def esPar(x: Int): Boolean = x % 2 == 0
val numerosPares = numeros.filter(esPar)
// numerosPares: List[Int] = List(2, 4)

numerosPares
// res15: List[Int] = List(2, 4)
```

## *find*

Devuelve el primer elemento (opcional) de la colección que cumple el predicado:

```scala
val primeroMayorQue3 = numeros.find((x: Int) => x > 2)
// primeroMayorQue3: Option[Int] = Some(3)

primeroMayorQue3
// res16: Option[Int] = Some(3)
```

## *partition*

Divide una colección en dos siguiendo un predicado:

```scala
val (numPares, numImpares) = numeros.partition(esPar)
// numPares: List[Int] = List(2, 4)
// numImpares: List[Int] = List(1, 3, 5)

numPares
// res17: List[Int] = List(2, 4)

numImpares
// res18: List[Int] = List(1, 3, 5)
```

## *zip*

Combina como producto cartesiano el contenido de dos colecciones en una:

```scala
val caracteres = List('a', 'b', 'c', 'd', 'e')
// caracteres: List[Char] = List('a', 'b', 'c', 'd', 'e')

caracteres.zip(numeros)
// res19: List[(Char, Int)] = List(
//   ('a', 1),
//   ('b', 2),
//   ('c', 3),
//   ('d', 4),
//   ('e', 5)
// )
```

## *dropWhile*

*Dropa* elementos de la colección mientras se cumpla el predicado:

```scala
val mayoresDe3 = numeros.dropWhile(_ <= 3)
// mayoresDe3: List[Int] = List(4, 5)

mayoresDe3
// res20: List[Int] = List(4, 5)
```

## *foldLeft*

*Pliega* una colección sobre un elemento que actúa como acumulador:

```scala
val sumaNumeros = numeros.foldLeft(0)((acumulado: Int, elemento: Int) => acumulado + elemento) 
// sumaNumeros: Int = 15 

sumaNumeros
// res21: Int = 15
```

Visualmente:

```scala
numeros.foldLeft(0)((acumulado: Int, elemento: Int) => {
    println("acumulado: %d, elemento: %d".format(acumulado, elemento))
    acumulado + elemento
})
// acumulado: 0, elemento: 1
// acumulado: 1, elemento: 2
// acumulado: 3, elemento: 3
// acumulado: 6, elemento: 4
// acumulado: 10, elemento: 5
// res22: Int = 15
```

## *foldRight*

Equivalente a *foldLeft* pero aplicando la función por la derecha:

```scala
numeros.foldRight(0)((acumulado: Int, elemento: Int) => {
    println("acumulado: %d, elemento: %d".format(acumulado, elemento))
    acumulado - elemento
})
// acumulado: 5, elemento: 0
// acumulado: 4, elemento: 5
// acumulado: 3, elemento: -1
// acumulado: 2, elemento: 4
// acumulado: 1, elemento: -2
// res23: Int = 3
```

## *flatten*

*Aplana* un nivel en una colección de colecciones:

```scala
val listaDeListas = List(List(1, 2), List(3, 4))
// listaDeListas: List[List[Int]] = List(List(1, 2), List(3, 4))
listaDeListas.flatten
// res24: List[Int] = List(1, 2, 3, 4)
```

## *flatMap*

Combina un el *mapping* con el *flatting*:

```scala
listaDeListas.flatMap(l => l.map(_ * 2))
// res25: List[Int] = List(2, 4, 6, 8)
```

## *foreach*

Aplica una sentencia a cada elemento de la colección y no devuelve nada:

```scala
numeros.foreach { x =>
    println(x)
}
// 1
// 2
// 3
// 4
// 5
```

