import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GameTest {
    // Beispielhafter Spieltag

    val game1 = Game(1, "Wolfsburg", 2, "Stuttgart", 0)
    val game2 = Game(1, "Mainz", 2, "Bayer 04", 2)
    val game3 = Game(1, "Hertha", 1, "Hannover", 0)
    val game4 = Game(1, "Bremen", 2, "Frankfurt", 3)
    val game5 = Game(1, "Nürnberg", 1, "Schalke", 2)
    val game6 = Game(1, "Dortmund", 1, "1. FC Köln", 0)
    val game7 = Game(1, "Hoffenheim", 1, "Bayern", 1)
    val game8 = Game(1, "Bochum", 3, "Gladbach", 3)
    val game9 = Game(1, "Freiburg", 1, "Hamburg", 1)

    val day1 = Cons(game1, Cons(game2, Cons(game3, Cons(game4, Cons(game5, Cons(game6, Cons(game7, Cons(game8, Cons(game9, Empty)))))))))

    @Test
    fun homePointsTest() {
        assertEquals(Points.THREE, homePoints(game1))
        assertEquals(Points.ONE, homePoints(game2))
        assertEquals(Points.THREE, homePoints(game3))
        assertEquals(Points.ZERO, homePoints(game4))
    }

    @Test
    fun guestPointsTest() {
        assertEquals(Points.ZERO, guestPoints(game1))
        assertEquals(Points.ONE, guestPoints(game2))
        assertEquals(Points.ZERO, guestPoints(game3))
        assertEquals(Points.THREE, guestPoints(game4))
    }

    @Test
    fun drawnGamesTest() {
        assertEquals(Cons(game2, Cons(game7, Cons(game8, Cons(game9, Empty)))),
            drawnGames(day1))
    }

}