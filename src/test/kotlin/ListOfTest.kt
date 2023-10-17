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

    @Test
    fun countZeroesTest() {
        assertEquals(0, countZeroes(Empty))
        assertEquals(0, countZeroes(Cons(1, Cons(2, Empty))))
        assertEquals(2, countZeroes(Cons(0, Cons(1, Cons(0, Empty)))))
    }

    @Test
    fun containsGreaterTenTest() {
        assert(containsGreaterTen(Cons(11, Empty)))
        assert(!containsGreaterTen(Empty))
        assert(!containsGreaterTen(Cons(1, Cons(2, Empty))))
    }

    @Test
    fun deleteOnceTest() {
        assertEquals(Empty, deleteOnce(Empty, 42))
        assertEquals(Cons(1, Cons(2, Empty)),
            deleteOnce(Cons(1, Cons(2, Empty)), 42))
        assertEquals(Cons(1, Empty),
            deleteOnce(Cons(42, Cons(1, Empty)), 42))
        assertEquals(Cons(1, Cons(42, Empty)),
            deleteOnce(Cons(1, Cons(42, Cons(42, Empty))), 42))
    }

    @Test
    fun sortTest() {
        assertEquals(Empty, sortInts(Empty))
        assertEquals(Cons(1, Empty), sortInts(Cons(1, Empty)))
        assertEquals(Cons(1, Cons(2, Cons(3, Empty))),
            sortInts(Cons(2, Cons(1, Cons(3, Empty)))))
    }
}