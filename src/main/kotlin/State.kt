import kotlin.math.abs

// imperative apis pure machen

val rng = kotlin.random.Random

// wir wissen eigentlich gar nichts darüber, was "in" rng passiert
// nextDouble() +-------------+
// -----------> | rng: Random |
//              +-------------+
//              |             |
//              |  +--------+ |
//              |  | state 1| |
//              |  +--------+ |
//              |             |
//              +-------------+
//                   |
//                   v Mutation!
// nextDouble() +-------------+
// -----------> | rng: Random |
//              +-------------+
//              |             |
//              |  +--------+ |
//              |  | state 2| |
//              |  +--------+ |
//              |             |
//              +-------------+
//                   |
//                   v Mutation!
// nextInt()    +-------------+
// -----------> | rng: Random |
//              +-------------+
//              |             |
//              |  +--------+ |
//              |  | state 3| |
//              |  +--------+ |
//              |             |
//              +-------------+

fun rollDie(): Int {
    val rng = kotlin.random.Random
    return rng.nextInt(6)
}

// Hier hat sich ein Fehler eingeschlichen (off-by-one)
// Aber: Wir finden den Fehler nur jedes 6te mal (wenn wir eine 0 wüfeln)

// 1. Schritt: Funktion "deterministischer machen"
fun rollDie2(rng: kotlin.random.Random): Int = rng.nextInt(6)

// Besser, aber: jetzt müssen wir sicherstellen,
// dass wir den _selben_ Generator (wieder-)verwenden
// - wir wollen den _selben_ Generator
// - mit dem selben Zustand!

// Ok, jetzt funktional, mit allem, was wir bisher gelernt haben

sealed interface RNG {
    /**
     * Erzeuge eine zufällige Ganzahl und den "nächsten" RNG
     */
    fun nextInt(): Pair<Int, RNG>
}

data class SimpleRNG(val seed: Long): RNG {
    // https://en.wikipedia.org/wiki/Linear_congruential_generator
    override fun nextInt(): Pair<Int, RNG> {
        // mit dem aktuellen seed den nächsten seed erzeugen
        val newSeed = (seed * 0x5DEECE66DL + 0xBL) and 0xFFFFFFFFFFFFL
        // mit dem nächsten seed das nächste RNG erzeugen
        val nextRNG = SimpleRNG(newSeed)
        // die pseudo-zufällig Ganzzahl produzieren
        val n = (newSeed ushr 16).toInt()
        // das Tupel aus (randomNumber, nextRNG) zurückgeben
        return n to nextRNG
    }
}

fun randomPair(rng: RNG): Pair<Pair<Int, Int>, RNG> {
    val (i1, rng2) = rng.nextInt()
    val (i2, rng3) = rng2.nextInt()
    return (i1 to i2) to rng3
}

// Typalias um Zustandsübergänge zu repräsentieren
typealias Rand<A> = (RNG) -> Pair<A, RNG>
// Was ist das?  Ein Programm, das, gegeben ein RNG,
// ein A und den nächsten RNG erzeugt

val intR: Rand<Int> = { rng -> rng.nextInt() }

/**
 * Erzeuge eine zufällige positive Zahl
 */
fun nonNegativeInt(rng: RNG): Pair<Int, RNG> {
    val (i, rng2) = rng.nextInt()
    return abs(i) to rng2
}

// So allein nützt das aber nicht viel

// Was ist der allereinfachste RNG?
// Einer, der nichts tut, sondern nur sein Argument zurück
// gibt und den Zustand nicht anfasst!


// Wir können den Output verändern

/**
 * Erzeugt ein Rand<B> aus einem Rand<A>,
 * indem `f` auf das Ergebnis von `rng` angewendet wird.
 */
fun <A, B> map(s: Rand<A>, f: (A) -> B): Rand<B> =
    { rng ->
        val (a, rng2) = s(rng)
        f(a) to rng2
    }

// Damit können wir jetzt aus einem Rand ein anderes machen

/**
 * Rand der nur gerade, positive Ganzzahlen erzeugt
 */
fun nonNegativeEven(): Rand<Int> =
    map(::nonNegativeInt) { (it * 2) }

// Soweit, so gut.  Wir können einzelne Rands hinschreiben und verwenden.
// In der Realität wollen wir aber auch "Sequenzen" von
// solchen Rands haben, also viele Aufrufe nacheinander.
// Fangen wir damit an, zwei Rands zu kombinieren
fun <A, B, C> map2(ra: Rand<A>, rb: Rand<B>, f: (A, B) -> C): Rand<C> =
    { r1: RNG ->
        val (a, r2) = ra(r1)
        val (b, r3) = rb(r2)
        f(a, b) to r3
    }

/**
 * Kombinator der das Ergebnis von zwei Rands als Paar zurückgibt.
 */
fun <A, B> both(ra: Rand<A>, rb: Rand<B>): Rand<Pair<A, B>> =
    map2(ra, rb) { a, b -> a to b}

val doubleR: Rand<Double> =
    map(::nonNegativeInt) { i ->
        i / (Int.MAX_VALUE.toDouble() + 1)
    }

val intDoubleR: Rand<Pair<Int, Double>> = both(intR, doubleR)
val doubleIntR: Rand<Pair<Double, Int>> = both(doubleR, intR)


/**
 * Kombiniere eine Liste von Rands in einen Rand einer Liste
 */
fun <A> sequence(fs: ListOf<Rand<A>>): Rand<ListOf<A>> =
    fs.reduce(unit(fs.empty())) { acc, f ->
        map2(f, acc) { h, t ->
            Cons(h, t)
        }
    }


// Was haben wir erreicht?
// - zuerst haben wir bestehenden Zustand mutiert
// - dann haben wir eine elegantere API entwickelt,
//   um die Zustandsübergänge zu verstecken
// - bei sequence haben wir gesehen,
//   dass wir nicht ein mal mehr explizit das `rand` Argument sehen
// - mit map und map2 können wir mit sehr wenig Code Funktionen schreiben,
//   die sonst sehr mühselig wären

/**
 * Eine Zufallszahl generieren, die kleiner ist als `n`.
 */
fun nonNegativeLessThan(n: Int): Rand<Int> = map(::nonNegativeInt) { it % n }

/**
 * Eine Zufallszahl außer `n` generieren.
 */
// fun randomBut(n: Int): Rand<Int> = map(::nonNegativeInt) { it != n }

fun randomBut(n: Int): Rand<Int> =
    map(::nonNegativeInt) { res ->
        if (res != n) {
            res
        } else {
            // randomBut(n)
            TODO()
        }
    }

/**
 * Random generator der sein Argument und ein
 * RNG zurück gibt, ohne RNG zu verändern.
 */
// return und bind
// pure und flatMap

// unit 'hebt' einen Wert in einen Kontext
fun <S, A> unit(a: A): State<S, A> = { state -> a to state }

// hintereinanderauführung von funktionen mit kontext
fun <A, B> flatMap(f: Rand<A>, g: (A) -> Rand<B>): Rand<B> =
    { rng ->
        val (resA, rngA) = f(rng)
        val resB: (RNG) -> Pair<B, RNG> = g(resA)
        resB(rngA)
    }

fun rollTwoDice(): Pair<Int, Int> {
    val rng = kotlin.random.Random
    val a = rng.nextInt()
    val b = rng.nextInt()
    return a to b
}

fun rollDiceS(): Rand<Int> =
    map(nonNegativeLessThan(6)) { it + 1}

fun rollTwoDice2(): Rand<Pair<Int, Int>> =
    flatMap(rollDiceS()) { firstResult ->
        flatMap(rollDiceS()) { secondResult ->
            unit(firstResult to secondResult)
        }
    }

fun randomButAberRichtig(n: Int): Rand<Int> =
    flatMap(::nonNegativeInt) { i  // das vorherige ergebnis
        ->
        if (i != n) unit(i)
        else randomButAberRichtig(n)
    }



//    { rng ->
//        when (fs) {
//            is Empty -> unit(Empty)(rng)
//            is Cons -> {
//                val (a, nextRng) = fs.first(rng)
//                val (b, lastRng) = sequence(fs.rest)(nextRng)
//                Cons(a,b) to lastRng
//
//            }
//        }
//    }
// typealias Rand<A> = (RNG) -> Pair<A, RNG>

typealias State<S, A> = (S) -> Pair<A, S>
typealias Rand<A> = State<RNG, A>


fun doit(): Boolean {
    val rng = SimpleRNG(42)
    val (zahl, nextRng) = rng.nextInt()
    val (gleicheZahl, gleicherNextRng) = rng.nextInt()

    return zahl == gleicheZahl

}