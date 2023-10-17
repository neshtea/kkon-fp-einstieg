import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ListOfTest {
    @Test
    fun listLengthTest() {
        assertEquals(3, listLength(Cons(1, Cons(2, Cons(3, Empty)))))
        assertEquals(2, listLength(Cons("Marco", Cons("Markus", Empty))))
    }
}