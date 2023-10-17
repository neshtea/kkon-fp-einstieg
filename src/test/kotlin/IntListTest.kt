import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IntListTest {
    val list0 = emptyIntList  // []
    val list1 = ConsList(42, emptyIntList)  // [42]
    val list2 = ConsList(1, ConsList(2, ConsList(3, emptyIntList)))  // [1,2,3]
    val list3 = ConsList(1, ConsList(2, ConsList(3, ConsList(4, emptyIntList))))  // [1,2,3,4]

    @Test
    fun listSumTest() {
        assertEquals(0, listSum(emptyIntList))
        assertEquals(42, listSum(list1))
        assertEquals(6, listSum(list2))
    }

    @Test
    fun listProductTest() {
        assertEquals(1, listProduct(emptyIntList))
        assertEquals(42, listProduct(list1))
        assertEquals(24, listProduct(list3))
    }
}