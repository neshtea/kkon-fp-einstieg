// Ein Fluss ist eines der Folgenden
// - ein Bach aus einer Quelle
// - ein Zusammentreffen von einem Hauptfluss und einem Nebenfluss
sealed interface River

// Ein Bach hat folgende Eigenschaften:
// - Ursprungsort
data class Creek(val origin: String): River

// Ein Zusammentreffen besteht aus:
// - Ort (Name eines Ortes)
// - Nebenfluss
// - Hauptfluss
data class Confluence(
    val location: String,
    val mainStream: River,
    val tributary: River
    ): River

/**
 * Fließt Wasser von einem Ort in Fluss?
 */
fun flowsFrom(location: String, river: River): Boolean =
    when (river) {
        is Creek -> river.origin == location
        is Confluence ->
            // Ja, wenn
            // - der Zusammenfluss gerade bei `location` statt findet
            //   ODER
            // - Wasser von `location` in den Hauptfluss fließt
            //   ODER
            // - Wasser von `location` in den Nebenfluss fließt.
            river.location == location
                    || flowsFrom(location, river.mainStream)
                    || flowsFrom(location, river.tributary)
    }