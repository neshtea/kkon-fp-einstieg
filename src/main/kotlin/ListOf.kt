import kotlin.math.min

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
fun runOverDillos(dillos: ListOf<Dillo>): ListOf<Dillo> =
    when (dillos) {
        is Empty -> Empty
        is Cons ->
            Cons(runOverDillo(dillos.first),
                runOverDillos(dillos.rest))
    }

/**
 * Finde alle lebenden Dillos in einer Liste.
 */
fun liveDillos(dillos: ListOf<Dillo>): ListOf<Dillo> =
    when (dillos) {
        is Empty -> Empty
        is Cons ->
            if (dillos.first.alive) {
                Cons(dillos.first, liveDillos(dillos.rest))
            } else {
                liveDillos(dillos.rest)
            }
    }

// Ein Resultat ist eines der folgendne
// - nur ein Ergebnis
// - nichts
sealed interface Maybe<out A>

// Ein Ergebnis besteht aus dem Resultat
data class Just<A>(val value: A): Maybe<A>

data object None: Maybe<Nothing>



/**
 * Das minimum einer Liste von Integern zurück geben.
 */
fun listMin(xs: ListOf<Int>): Maybe<Int> =
    when (xs) {
        is Empty -> None
        is Cons ->
            when (val restMin: Maybe<Int> = listMin(xs.rest)) {
                is None -> Just(xs.first)
                is Just ->
                    Just(min(restMin.value, xs.first))
            }
    }

/**
 * Anzahl der Nullen in einer Liste von Ints finden.
 */
fun countZeroes(xs: ListOf<Int>): Int =
    when (xs) {
        is Empty -> 0
        is Cons ->
            if (xs.first == 0) {
                1 + countZeroes(xs.rest)
            } else
                countZeroes(xs.rest)
    }

/**
 * Enthält eine Liste eine Zahl > 10.
 */
fun containsGreaterTen(xs: ListOf<Int>): Boolean =
    when (xs) {
        is Empty -> false
        is Cons ->
            xs.first > 10 || containsGreaterTen(xs.rest)
    }

/**
 * Lösche das erste Vorkommen einer bestimmten Zahl aus einer Liste von Ints.
 */
fun deleteOnce(xs: ListOf<Int>, delete: Int): ListOf<Int> =
    when (xs) {
        is Empty -> Empty
        is Cons ->
            if (xs.first == delete) {
                xs.rest
            } else {
                Cons(xs.first, deleteOnce(xs.rest, delete))
            }
    }

/**
 * Sortiere eine Liste von Integern aufsteigend.
 */
fun sortInts(xs: ListOf<Int>): ListOf<Int> =
    when (xs) {
        is Empty -> Empty
        is Cons -> {
            when (val m: Maybe<Int> = listMin(xs)) {
                is None -> Empty
                is Just ->
                    Cons(m.value,
                        sortInts(deleteOnce(xs, m.value)))
            }
        }
    }