// Ein Haustier ist eines der folgenden
// - Katze
// - Hund
// - Schlange

//## Konstruktionsanleitung 6 (Datenanalyse)
//
//Suche in der Aufgabenstellung nach problemrelevanten Größen; Kandidaten sind
//immer die Substantive. Schreibe für jede dieser Größen eine Datendefinition, es
//sei denn, diese ist aus dem Kontext offensichtlich.  Wenn es für die
//Datendefinition noch keine Signatur gibt, schreibe eine Signatur-Definition
//dazu. Schreibe außerdem Beispiele auf und schreibe jeweils einen Kommentar, der
//die Beziehung zwischen Daten und Information beschreibt.

enum class Pet {
    CAT, DOG, SNAKE;

    fun isCute(): Boolean = isCute(this)
}

//'hinter'/'nach'
//f o g

/**
 * Is ein Haustier süß? (nach Marcos Meinung)
 */
fun isCute(pet: Pet): Boolean =
    when (pet) {
        Pet.CAT -> true
        Pet.DOG -> true
        Pet.SNAKE -> false
    }
