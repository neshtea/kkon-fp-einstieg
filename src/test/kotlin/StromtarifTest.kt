import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertEquals
import kotlin.test.Test

class StromtarifTest {
    @Test
    fun billigStromTest() {
        assertEquals(4.9, billigStrom(0), 0.01)
        assertEquals(6.8, billigStrom(10), 0.01)
        assertEquals(8.7, billigStrom(20), 0.01)
        assertEquals(10.6, billigStrom(30), 0.01)
    }

    @Test
    fun wattFuerrWenig() {
        assertEquals(8.2, wattFuerWenig(0), 0.01)
        assertEquals(9.8, wattFuerWenig(10), 0.01)
    }

    @Test
    fun stromtarifRechnungsbetrag() {
        assertEquals(4.90, stromtarifRechnungsbetrag(4.90, 0.19, 0), 0.01)
        assertEquals(6.80, stromtarifRechnungsbetrag(4.90,0.19, 10), 0.01)
        assertEquals(8.70, stromtarifRechnungsbetrag(4.90, 0.19, 20), 0.01)
        assertEquals(10.60, stromtarifRechnungsbetrag(4.90, 0.19, 30), 0.01)
        assertEquals(8.20, stromtarifRechnungsbetrag(8.20, 0.16, 0), 0.01)
        assertEquals(9.8, stromtarifRechnungsbetrag(8.20, 0.16, 10), 0.01)
        assertEquals(11.4, stromtarifRechnungsbetrag(8.20, 0.16, 20), 0.01)
        assertEquals(13.0, stromtarifRechnungsbetrag(8.20, 0.16, 30), 0.01)
    }
}