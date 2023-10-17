// singly linked list

// Eine Liste aus Zahlen ist eines der folgenden:
// - eine leere Liste
// - eine Cons-Liste

sealed interface ListOf<out A>

// Eine cons (nicht leer) Liste besteht aus
// - dem ersten Element
// - einer Liste aus Zahlen mit den restlichen Elementen
data class Cons<out A>(val first: A, val rest: ListOf<A>): ListOf<A>

data object Empty: ListOf<Nothing>

/**
 * Länge einer Liste ausrechnen.
 */
fun <A> listLength(xs: ListOf<A>): Int =
    when (xs) {
        is Empty -> 0
        is Cons ->
           1 + listLength(xs.rest)
    }

/**
 * Eine Liste von Dillos überfahren
 */