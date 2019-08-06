#*Pattern-matching* o reconocimiento de patrones

## *Match* de valores

```scala
val numeroVeces = 1
// numeroVeces: Int = 1
// numeroVeces: Int = 1

numeroVeces match {
    case 1 => "uno"
    case 2 => "dos"
    case _ => "algún otro número"
}
// res0: String = "uno"
```

En un bloque `match` se pueden especificar las distintos valores con los que puede *matchear* usando expresiones `case ... =>`. Para el caso por defecto o que *matchearía* con cualquier valor se usa `case _ =>`.

## *Match* con guardias

A las expresiones `case` se les puede añadir una condición:

```scala
numeroVeces match {
    case i if i == 1 => "uno"
    case i if i == 2 => "dos"
    case _ => "algún otro número"
}
// res1: String = "uno"
```

Señalar que si no especificamos todos los casos en el *match* el compilador generará un mensaje de advertencia.

## *Match* con tipos

Se puede utilizar el *match* con elementos de distintos tipos:

```scala
def algoMayor(unValor: Any): Any = {
    unValor match {
        case i: Int if i < 0 => i - 1
        case i: Int => i + 1
        case d: Double if d < 0.0 => d - 0.1
        case d: Double => d + 0.1
        case otraCosa => otraCosa + " ampliado"
    }
}

algoMayor(-7)
// res2: Any = -8

algoMayor(41)
// res3: Any = 42

algoMayor(-273.0)
// res4: Any = -273.1

algoMayor(101.23)
// res5: Any = 101.33

algoMayor("elemento")
// res6: Any = "elemento ampliado"
```

