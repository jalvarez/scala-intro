#*Pattern-matching* o reconocimiento de patrones

## *Match* de valores

```scala mdoc
val numeroVeces = 1
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
```scala mdoc

numeroVeces match {
    case i if i == 1 => "uno"
    case i if i == 2 => "dos"
    case _ => "algún otro número"
}
```

Señalar que si no especificamos todos los casos en el *match* el compilador generará un mensaje de advertencia.

## *Match* con tipos

Se puede utilizar el *match* con elementos de distintos tipos:
```scala mdoc
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

algoMayor(41)

algoMayor(-273.0)

algoMayor(101.23)

algoMayor("elemento")
```