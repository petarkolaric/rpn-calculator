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

    @Test(expected = IllegalStateException::class)
    fun `should throw exception if trying to undo last operation when nothing to undo`() {
        val stack = Stack()

        stack.revertState()
    }

    @Test
    fun `should be able to go back to saved state`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))
        stack.saveState()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("60"))
        stack.push(BigDecimal("70"))

        stack.revertState()

        assertEquals("10 20", stack.toString())
    }

    @Test(expected = IllegalStateException::class)
    fun `should throw exception when no state to revert to`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))
        stack.saveState()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("60"))
        stack.push(BigDecimal("70"))

        stack.revertState()
        stack.revertState()

        assertEquals("10 20", stack.toString())
    }

    @Test
    fun `should be able to clear stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))

        stack.clear()

        assertEquals("", stack.toString())
    }

//    @Test
//    fun `to string should not return trailing zeros after decimal place`() {
//
//    }
}