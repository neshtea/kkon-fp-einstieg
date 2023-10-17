import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ComputerTest {
    // Computer mit 32 gb ram und 1tb festplatte
    val gamer = Computer("Cell", 32, 1000)

    // Computer mit 8 gb ram und 500gb festplatte
    val workstation = Computer("Xeon", 8, 500)

    @Test
    fun totalMemoryTest() {
        assertEquals(508, totalMemory(workstation))
        assertEquals(1032, totalMemory(gamer))
    }
}