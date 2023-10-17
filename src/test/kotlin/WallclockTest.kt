import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class WallclockTest {
    val wt1 = Wallclock(11, 55)  // fünf vor zwölf
    val wt2 = Wallclock(0, 0)  // Mitternacht
    val wt3 = Wallclock(1, 1)  // 1 Uhr 1

    @Test
    fun minutenNachMitternacht() {
        assertEquals(11 * 60 + 55, minutenNachMitternacht(wt1))
        assertEquals(0, minutenNachMitternacht(wt2))
        assertEquals(61, minutenNachMitternacht(wt3))
    }
    @Test
    fun minutesSiceMidnightToWallclockTimeTest() {
        assertEquals(wt1, minutesSinceMidnightToWallclock(11 * 60 + 55))
        assertEquals(wt2, minutesSinceMidnightToWallclock(0))
        assertEquals(wt3, minutesSinceMidnightToWallclock(61))
    }

}