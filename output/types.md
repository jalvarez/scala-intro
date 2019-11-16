# ¿Qué son los tipos estáticos y porqué son útiles

Los tipos nos permiten especificar el dominio y codominio de una función. Cuando en matemáticas tenemos una función como:

```
f: R -> N
```

Nos dice que la función `f` "mapea" valores de un conjunto de números reales `R` a un conjunto de números naturales `N`. Estos conjuntos serían tipos, por lo que un sistema de tipos es una de las formas más potentes de expresar estos conjuntos.

Dadas estas anotaciones, el compilador puede comprobar en tiempo de compilación (estáticamente) que el uso de la función es correcta.

Aumentando la expresividad del sistema de tipos, podemos producir un código más fiable ya que nos permite comprobar invariantes sobre nuestro programa antes de que se ejecute. Indicar que toda la información sobre tipos es eliminada después de la compilación cuando ya no es necesaria, a esto se denomina *erasure* (borrado).

# Tipos en scala

El sistema de tipos es potente y con gran expresividad. Alguna de sus características más destacadas son:
* **Polimorfismo paramétrico** o programación con genéricos.
* **Inferencia de tipos**, que nos evita especificar en las declaraciones de valores o variables.
* **Cuantificación existencial** o definir algo para un tipo anónimo.
* **Vistas** o adaptación de valores de un tipo en otro.

## Polimorfismo paramétrico

Nos permite escribir código genérico, es decir que manipule valores de diferentes tipos, sin comprometer la potencia del tipado estático.

Por ejemplo una función que saque el primer elemento de un lista podría ser:

```scala
def saca1[A](l: List[A]): List[A] = l.tail
```

Donde `A` se denomina parámetro de tipo y se puede usar en los parámetros y el valor de retorno de la función.

Se puede usar con una lista de enteros:

```scala
saca1(List(1,2,3))
// res0: List[Int] = List(2, 3)
```

O con cadenas de texto:

```scala
saca1(List("hello", "how are you?", "bye"))
// res1: List[String] = List("how are you?", "bye")
```

## Inferencia de tipos

Uno de los inconvenientes del tipado estático que produce una sobrecarga en la sintáxis del código. Pero scala alivia esto mediante la inferencia de tipos, que usa las limitaciones especificadas para intentar unificarlas en un tipo.

Por ejemplo, si definimos una función *identidad* así:

```scala
def id[T](x: T) = x
```

Usando con distintos tipos en sus parámetros el lenguaje determina el tipo de salida:

```scala
val x1 = id(322)
// x1: Int = 322

val x2 = id("hey")
// x2: String = "hey"

val x3 = id(Array(1,2,3,4))
// x3: Array[Int] = Array(1, 2, 3, 4)
```

Los tiempos se mantienen y scala infiere el parámetro de tipo `T` por nosotros aunque no hallamos especificado el tipo de retorno de la función explícitamente.

## Varianza

El sistema de tipos tiene en cuenta la jerarquía de clases junto con el polimorfismo.
La jerarquía de clases permiten expresar las relaciones entre subtipos, la cuestión que surge al introducir el polimorismo es: si `S` es una subclase de `T`, ¿se considera `Contenedor[S]` una subclase de `Contenedor[T]`?.

Las anotaciones de varianza nos permiten expresar estas relaciones:

|                 |            Significado             | Notación en scala |
|-----------------|------------------------------------|-------------------|
| *invariant*     | `C[T]` y `C[S]` no tienen relación | `[T]`             |
| *covariant*     | `C[S]` es una subclase de `C[T]`   | `[+T]`            |
| *contravariant* | `C[T]` es una subclase de `C[S]`   | `[-T]`            |

La relación de subclase significa que dado un tipo `T`, si `S` es un subtipo ¿puedes sustituirlo?

```scala
class Covariant[+A]

val cov: Covariant[AnyRef] = new Covariant[String]

val cow: Covariant[String] = new Covariant[AnyRef]
// error: type mismatch;
//  found   : repl.Session.App.Covariant[AnyRef]
//  required: repl.Session.App.Covariant[String]
// val cow: Covariant[String] = new Covariant[AnyRef]
//                              ^^^^^^^^^^^^^^^^^^^^^
```

```scala
class Contravariant[-A]

val cnw: Contravariant[String] = new Contravariant[AnyRef]

val cnv: Contravariant[AnyRef] = new Contravariant[String]
// error: type mismatch;
//  found   : repl.Session.App.Contravariant[String]
//  required: repl.Session.App.Contravariant[AnyRef]
// val cnv: Contravariant[AnyRef] = new Contravariant[String]
//                                  ^^^^^^^^^^^^^^^^^^^^^^^^^
```

La contravarianza parece extraña, ¿cuándo se usa?.

Como ejemplo el tipo para la funciones se definen así en scala:

```scala
trait Function1 [-T1, +R] extends AnyRef
```

Donde el tipo del parámetro de entrada `-T1` se define como contravariante.

Desde el punto de vista de la sustitución, si tenemos un jerarquía simple como:

```scala
class Animal { val sonido = "fizzzz" }

class Pájaro extends Animal { override val sonido = "pio" }

class Pato extends Pájaro { override val sonido = "quack" }
```

Si necesitamos una función que tenga como parámetro un `Pájaro`:

```scala
val hacerSonar: (Pájaro => String) = ???
```

Y disponemos de dos funciones que hacen sonar animales y patos:

```scala
val hacerSonarAnimal: (Animal => String) = { a => a.sonido }
// hacerSonarAnimal: Animal => String = <function1>
val hacerSonarPato: (Pato => String) = { p => p.sonido }
// hacerSonarPato: Pato => String = <function1>
```

Solo podremos reutiilizar la función de hacer sonar al animal siendo `Animal` una superclase de `Pájaro`:

```scala
val hacerSonar: (Pájaro => String) = hacerSonarAnimal
// hacerSonar: Pájaro => String = <function1>
```

Y no podemos usar la función que tiene `Pato` como parámetro, aunque esta sea una subclase de `Pájaro`.

```scala
val hacerSonar2: (Pájaro => String) = hacerSonarPato
// error: type mismatch;
//  found   : repl.Session.App.Pato => String
//  required: repl.Session.App.Pájaro => String
// val hacerSonar2: (Pájaro => String) = hacerSonarPato
//                                       ^^^^^^^^^^^^^^
```

## Límites

...

## Cuantificación

...
