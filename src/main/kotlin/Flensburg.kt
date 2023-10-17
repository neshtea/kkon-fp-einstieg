//An die "Flensburg"-Punkte, die es bei Verstößen gegen die Verkehrsvorschriften
//gibt, knüpft §4 des Straßenverkehrsgesetzes die folgenden Sanktionen:
//
//| Bedingung      | Sanktionen       |
//|----------------+------------------|
//| 0 Punkte       | Keine Sanktionen |
//| 1 bis 3 Punkte | Vormerkung       |
//| 4 bis 5 Punkte | Ermahnung        |
//| 6 bis 7 Punkte | Verwarnung       |
//| 8 Punkte       | Entzug           |
//
//Schreibe eine Funktion, die zu einer gegebenen Punktezahl die daraus folgende
//Konsequenz berechnet!

// Eine Sanktion ist eines der folgenden
// - keine sanktion
// - vormerkung
// - ermahnung
// - verwarnung
// - entzug
enum class Sanktion {
    KEINE_SANKTION, VORMERKUNG, ERMAHNUNG, VERWARNUNG, ENTZUG
}

/**
 * Zu einer Punktzahl die entsprechende Sanktion berechnen.
 */
fun berechneSanktion(punktzahl: Int): Sanktion =
    when (punktzahl) {
        0 -> Sanktion.KEINE_SANKTION
        in 1..3 -> Sanktion.VORMERKUNG
        in 4..5 -> Sanktion.ERMAHNUNG
        in 6..7 -> Sanktion.VERWARNUNG
        else -> Sanktion.ENTZUG
    }