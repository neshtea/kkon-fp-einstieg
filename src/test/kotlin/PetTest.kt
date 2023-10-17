import org.junit.jupiter.api.Test

class PetTest {
    @Test
    fun isCuteTest() {
        assert(isCute(Pet.CAT))
        assert(isCute(Pet.DOG))
        assert(!isCute(Pet.SNAKE))
    }
}
