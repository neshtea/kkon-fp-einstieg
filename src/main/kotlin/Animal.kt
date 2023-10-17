// Ein Gürteltier hat folgende Eigenschaften:
// - Gewicht (in g)
// - lebendig oder tot

//data class Dillo(val weight: Int, val alive: Boolean)

/**
 * Gürteltier mit 500g Futter füttert
 */
fun feedDillo(dillo: Dillo): Dillo =
    // kotlinigere variante
    // dillo.copy(weight = dillo.weight + 500)
    if (dillo.alive) {
        Dillo(dillo.weight + 500, dillo.alive)
    } else {
        // Dillo(dillo.weight, dillo.alive)
        dillo
    }

/**
 * Gürteltier überfahren
 */
fun runOverDillo(dillo: Dillo): Dillo =
    // dillo.copy(alive = false)
    Dillo(dillo.weight, false)

// Ein Papagei hat folgende Eigenschaften:
// - Gewicht in Gramm
// - Satz, den er sagt
//data class Parrot(val weight: Int, val sentence: String)

// Ein Tier ist eines der folgenden:
// - ein Gürteltier
// - ein Papagei
sealed interface Animal

data class Dillo(val weight: Int, val alive: Boolean): Animal
data class Parrot(val weight: Int, val sentence: String): Animal

/**
 * Das Gewicht eines Tieres herauskriegen
 */
fun animalWeight(animal: Animal): Int =
    when (animal) {
        is Dillo -> animal.weight
        is Parrot -> animal.weight
    }