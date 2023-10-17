import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FlensburgTest {
    @Test
    fun berechneSanktionTest() {
        assertEquals(Sanktion.KEINE_SANKTION, berechneSanktion(0))
        assertEquals(Sanktion.VORMERKUNG, berechneSanktion(1))
        assertEquals(Sanktion.VORMERKUNG, berechneSanktion(3))
        assertEquals(Sanktion.ERMAHNUNG, berechneSanktion(4))
        assertEquals(Sanktion.ERMAHNUNG, berechneSanktion(5))
        assertEquals(Sanktion.VERWARNUNG, berechneSanktion(6))
        assertEquals(Sanktion.VERWARNUNG, berechneSanktion(7))
        assertEquals(Sanktion.ENTZUG, berechneSanktion(8))
    }
}