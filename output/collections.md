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

