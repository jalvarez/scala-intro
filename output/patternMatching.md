# *Pattern-matching* o reconocimiento de patrones

## *Match* de valores

```scala
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

## *Match* con *case class*

Una opción muy potente de scala es realizar el *match* usando una *case class*:

```scala
case class Calculadora(marca: String, modelo: String)

def tipoDeCalculadora(calc: Calculadora) = calc match {
  case Calculadora("HP", "20B") => "financiera"
  case Calculadora("HP", "48G") => "científica"
  case Calculadora("HP", "30B") => "empresarial"
  case Calculadora(marca, modelo) =>
    "La calculadora '%s %s' es de un tipo desconocido".format(marca, modelo)
}

val hp20b = Calculadora("HP", "20B")
// hp20b: Calculadora = Calculadora("HP", "20B")
val hp30b = Calculadora("HP", "30B")
// hp30b: Calculadora = Calculadora("HP", "30B")
val casio = Calculadora("Casio", "FX-50")
// casio: Calculadora = Calculadora("Casio", "FX-50")

tipoDeCalculadora(hp20b)
// res7: String = "financiera"

tipoDeCalculadora(hp30b)
// res8: String = "empresarial"

tipoDeCalculadora(casio)
// res9: String = "La calculadora 'Casio FX-50' es de un tipo desconocido"
```

## Excepciones

La gestión de excepciones en *scala* se realiza utilizando *pattern matching*:

```scala
try {
    println("Resultado ejecución %f".format(1 / 0))
}
catch {
    case e: Exception =>
        println("Se ha producido una error: %s".format(e.getMessage()))
}
finally {
    println("Finalizado")
}
// Se ha producido una error: / by zero
// Finalizado
```

Señalar que este código es solo para ejemplificar el uso del *pattern matching*, pero en *scala* existen técnicas más potentes para gestionar el código que puede generar excepciones.
