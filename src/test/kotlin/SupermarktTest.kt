import org.jetbrains.annotations.TestOnly
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class SupermarktTest {
    val heute = LocalDate.of(2023, 10, 17)
    val morgen = LocalDate.of(2023, 10, 18)
    val naechstenMonat = LocalDate.of(2023, 11, 17)
    val eier = Essen("Eier", 0.20, heute, 20)
    val cola = Getraenk("Cola", 1.99, naechstenMonat, 100, true)
    val kugelschreiber = Sonstiges("Kugelschreiber", 0.99, 5)

    @Test
    fun stueckpreisTest() {
        assertEquals(0.20, stueckpreis(eier))
        assertEquals(1.99, stueckpreis(cola))
        assertEquals(0.99, stueckpreis(kugelschreiber))
    }

    @Test
    fun reduziereBestandUmTest() {
        assertEquals(0, reduziereBestandUm(5, 6))
        assertEquals(5, reduziereBestandUm(10, 5))
    }

    @Test
    fun buchenTest() {
        assertEquals(Essen("Eier", 0.20, heute, 0),
            buchen(eier, 200))
        assertEquals(Getraenk("Cola", 1.99, naechstenMonat, 99, true),
            buchen(cola, 1))
        assertEquals(Sonstiges("Kugelschreiber", 0.99, 1),
            buchen(kugelschreiber, 4))
    }
    @Test
    fun istHaltbarTest() {
        assert(istHaltbar(eier, heute))
        assert(!istHaltbar(Essen("Eier", 0.19, morgen, 20), heute))
        assert(!istHaltbar(cola, heute))
        assert(istHaltbar(kugelschreiber, naechstenMonat))
    }

}