import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class StackTest {

    @Test
    fun `should be able to push to stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))

        assertEquals("10", stack.toString())
    }

    @Test
    fun `numbers in stack should be in correct order`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))
        stack.push(BigDecimal("30"))

        assertEquals("10 20 30", stack.toString())
    }

    @Test
    fun `should be able to pop items off stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        val poppedItem = stack.pop()

        assertEquals(BigDecimal("10"), poppedItem)
        assertEquals("", stack.toString())
    }

    @Test
    fun `should pop off the top of the stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))
        stack.push(BigDecimal("30"))
        val poppedItem = stack.pop()

        assertEquals(BigDecimal("30"), poppedItem)
        assertEquals("10 20", stack.toString())
    }

    @Test(expected = IllegalStateException::class)
    fun `should throw exception when no numbers to pop`() {
        val stack = Stack()
        stack.pop()
    }
}