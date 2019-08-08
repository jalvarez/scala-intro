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