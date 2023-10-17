import org.junit.jupiter.api.Test

class RiverTest {
    val eschach = Creek("Heimliswald")  // Quelle des Neckar
    val prim = Creek("Dreifaltigkeitsberg")  // Quelle des Neckar
    val neckar1 = Confluence("Rottweil", eschach, prim)  // erster Zusammenfluss des Neckar
    val schlichem = Creek("Tieringen")  // Zufluss des Neckar
    val neckar2 = Confluence("Epfendorf", neckar1, schlichem)

    @Test
    fun flowsFromTest() {
        assert(flowsFrom("Heimliswald", eschach))
        assert(!flowsFrom("Tübingen", eschach))
        assert(flowsFrom("Heimliswald", neckar2))
        assert(flowsFrom("Rottweil", neckar2))
        assert(!flowsFrom("Berlin", neckar2))
    }
}