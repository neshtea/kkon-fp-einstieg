// singly linked list

// Eine Liste aus Zahlen ist eines der folgenden:
// - eine leere Liste
// - eine Cons-Liste

sealed interface ListOfInt

// Eine cons (nicht leer) Liste besteht aus
// - dem ersten Element
// - einer Liste aus Zahlen mit den restlichen Elementen
data class ConsList(val first: Int, val rest: ListOfInt): ListOfInt

data object EmptyIntList: ListOfInt

val emptyIntList = EmptyIntList

/**
 * Alle werte aufsummieren.
 */
fun listSum(xs: ListOfInt): Int =
    when (xs) {
        is EmptyIntList -> 0
        is ConsList ->
            xs.first + listSum(xs.rest)
    }

/**
 * Das Produkt aller Listenelemente bilden.
 */
fun listProduct(xs: ListOfInt): Int =
    when (xs) {
        is EmptyIntList -> 1
        is ConsList ->
            xs.first * listProduct(xs.rest)
    }

// monoid
// - menge von objekten (bei uns Int)
// - binäre operation (bei uns *) -> das ergebnis der operation muss selbst in m liegen
// - neurtrales element (bei uns 1) -> muss assoziativ sein