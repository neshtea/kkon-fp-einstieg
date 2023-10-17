import java.time.LocalDate
import kotlin.math.max

//Ein Supermarkt möchte seine Waren in einem Programm verwalten. Es gibt drei
//Warenklassen:
//
//- *Essen* beschrieben durch einen Namen, den Stückpreis, das
//Mindesthaltbarkeitsdatum und den aktuellen Bestand im Supermarkt
//- *Getränke* beschrieben durch einen Namen, den Stückpreis, das
//Mindesthaltbarkeitsdatum und den Bestand. Zusätzlich muss hier noch
//festgehalten werden, ob Pfand verlangt wird.
//- *Sonstige* beschrieben durch einen Namen, den Stückpreis und den Bestand.

// Eine Ware ist eines der folgenden
// - ein Essen
// - ein Getraenk
// - eine sonstige Ware
sealed interface Ware {
    fun stueckpreis(): Double = stueckpreis(this)
}

// Ein Essen besteht aus
// - einem Namen
// - einem Stueckpreis
// - einem Mindesthaltbarkeitsdatum
// - dem aktuellen Bestand
data class Essen(
    val name: String,
    val stueckpreis: Double,
    val mdh: java.time.LocalDate,
    val bestand: Int
): Ware

// Ein Getraenk besteht aus
// - einem Namen
// - einem Stückpreis
// - einem Mindesthaltbarkeitsdatum
// - dem aktuellen Bestand
// - ob Pfand verlangt wird
data class Getraenk(
    val name: String,
    val stueckpreis: Double,
    val mdh: java.time.LocalDate,
    val bestand: Int,
    val kostetPfand: Boolean
): Ware

// Ein sonstiger Artikel besteht aus
// - einem Namen
// - einer Stückpreis
// - dem aktuellen Bestand
data class Sonstiges(
    val name: String,
    val stueckpreis: Double,
    val bestand: Int
): Ware

/**
 * Berechne den Stückpreis einer Ware.
 */
fun stueckpreis(ware: Ware): Double =
    when (ware) {
        is Essen -> ware.stueckpreis
        is Getraenk -> ware.stueckpreis
        is Sonstiges -> ware.stueckpreis
    }

/**
 * Eine Anzahl vom Bestand abziehen ohne unter 0 zu gehen.
 */
fun reduziereBestandUm(bestand: Int, abzug: Int): Int =
    max(0, bestand - abzug)

/**
 * Reduziere den Bestand einer Warenklasse um eine bestimmte Menge.
 */
fun buchen(ware: Ware, abzug: Int): Ware =
    when (ware) {
        is Essen -> Essen(
            ware.name,
            ware.stueckpreis,
            ware.mdh,
            reduziereBestandUm(
                ware.bestand, abzug
            )
        )
        is Getraenk -> Getraenk(
            ware.name,
            ware.stueckpreis,
            ware.mdh,
            reduziereBestandUm(
                ware.bestand, abzug
            ),
            ware.kostetPfand
        )
        is Sonstiges -> Sonstiges(
            ware.name,
            ware.stueckpreis,
            reduziereBestandUm(ware.bestand, abzug)
        )
    }

/**
 * Ist ein Datum kleiner oder gleich einem anderen Datum?
 */
fun datumKleinerGleich(d1: LocalDate, d2: LocalDate): Boolean =
    d1.isBefore(d2) || d1.isEqual(d2)
/**
 * Ist eine Ware an einem bestimmten Datum noch haltbar?
 */
fun istHaltbar(ware: Ware, datum: LocalDate): Boolean =
    when (ware) {
        is Essen -> datumKleinerGleich(ware.mdh, datum)
        is Getraenk -> datumKleinerGleich(ware.mdh, datum)
        else -> true
    }