import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class ListOfTest {
    @Test
    fun listLengthTest() {
        assertEquals(3, listLength(Cons(1, Cons(2, Cons(3, Empty)))))
        assertEquals(2, listLength(Cons("Marco", Cons("Markus", Empty))))
    }

    val dillo1 = Dillo(55000, true)  // 55kg, lebendig
    val dillo2 = Dillo(58000, false)  // 58kg, tot
    val dillo3 = Dillo(60000, true)  // 60kg, lebendig
    val dillo4 = Dillo(63000, false)  // 63kg, tot

    val highway75 = Cons(dillo1, Cons(dillo2, Cons(dillo3, Cons(dillo4, Empty))))

    @Test
    fun runOverDillosTest() {
        assertEquals(
            Cons(Dillo(55000, false), Cons(dillo2, Cons(Dillo(60000, false), Cons(dillo4, Empty)))),
            runOverDillos(highway75)
        )
    }

    @Test
    fun liveDillosTest() {
        assertEquals(Cons(dillo1, Cons(dillo3, Empty)), liveDillos(highway75))
    }

    @Test
    fun listMinTest() {
        assertEquals(None , listMin(Empty))
        assertEquals(Just(1), listMin(Cons(1, Cons(2, Cons(3, Empty)))))
    }
}