// Ein Spieltag hat folgende Eigenschaften
// - Spieltag
// - Heimmannschaft
// - Heimmannschaft-Tore
// - Gastmannschaft
// - Gastmannschaft-Tore

data class Game(
    val matchday: Int,
    val homeTeam: String,
    val homeGoals: Int,
    val guestTeam: String,
    val guestGoals: Int
)

enum class Points {
    ZERO, ONE, THREE
}

/**
 * Punktzahl für Heimmannschaft berechnen
 */
//fun homePoints(game: Game): Points {
//    val home = game.homeGoals
//    val guest = game.guestGoals
//
//    return if (home == guest) {
//        Points.ONE
//    } else if (home > guest) {
//        Points.THREE
//    } else {
//        Points.ZERO
//    }
//}

/**
 * Punktzahl für Gastmannschaft berechnen
 */
//fun guestPoints(game: Game): Points {
//    val home = game.homeGoals
//    val guest = game.guestGoals
//
//    return if (home == guest) {
//        Points.ONE
//    } else if (home < guest) {
//        Points.THREE
//    } else {
//        Points.ZERO
//    }
//}

fun homePoints(game: Game): Points =
    computePoints(::guestGoals, ::homeGoals, game)

fun guestPoints(game: Game): Points =
    computePoints(::homeGoals, ::guestGoals, game)

fun homeGoals(game: Game): Int = game.homeGoals
fun guestGoals(game: Game): Int = game.guestGoals

fun computePoints(getGoals1: (Game) -> Int, getGoals2: (Game) -> Int, game: Game): Points {
    val goals1 = getGoals1(game)
    val goals2 = getGoals2(game)

    return if (goals1 == goals2) {
        Points.ONE
    } else if (goals1 < goals2) {
        Points.THREE
    } else {
        Points.ZERO
    }
}

/**
 * Ist Spiel unentschieden?
 */
fun isDraw(game: Game): Boolean =
    homePoints(game) == Points.ONE

/**
 * Finde alle Spiele die unentschieden ausgegangen sind.
 */
fun drawnGames(games: ListOf<Game>): ListOf<Game> =
//    when (games) {
//        is Empty -> Empty
//        is Cons ->
//            if (isDraw(games.first)) {
//                Cons(games.first, drawnGames(games.rest))
//            } else {
//                drawnGames(games.rest)
//            }
//    }
    extract(games, ::isDraw)

