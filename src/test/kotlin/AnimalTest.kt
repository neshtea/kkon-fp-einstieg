import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AnimalTest {
    val dillo1 = Dillo(55000, true)  // 55kg, lebendig
    val dillo2 = Dillo(58000, false)  // 58kg, tot
    val dillo3 = Dillo(60000, true)  // 60kg, lebendig
    val dillo4 = Dillo(63000, false)  // 63kg, tot

    @Test
    fun feedDilloTest() {
        assertEquals(Dillo(55500, true), feedDillo(dillo1))
        assertEquals(dillo2, feedDillo(dillo2))
        assertEquals(Dillo(60500, true), feedDillo(dillo3))
        assertEquals(dillo4, feedDillo(dillo4))
    }

    @Test
    fun runOverDilloTest() {
        assertEquals(Dillo(55000, false), runOverDillo(dillo1))
        assertEquals(dillo2, runOverDillo(dillo2))
        assertEquals(Dillo(60000, false), runOverDillo(dillo3))
        assertEquals(dillo4, runOverDillo(dillo4))
    }


    val parrot1 = Parrot(10000, "Der Gärtner war's.")  // 10kg, Miss Marple
    val parrot2 = Parrot(5000, "Ich liebe dich.")  // 5kg, Romantiker

    @Test
    fun animalWeigthTest() {
        assertEquals(55000, animalWeight(dillo1))
        assertEquals(58000, animalWeight(dillo2))
        assertEquals(10000, animalWeight(parrot1))
        assertEquals(5000, animalWeight(parrot2))
    }
}