import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class CommandTest {

    @Test
    fun `add command should add two numbers`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("5"))

        AddCommand().execute(stack)

        assertEquals("15", stack.toString())
    }

    //TODO Think about how to implement this nicely/other exception tests
//    @Test
//    fun `should throw exception if not enough numbers to add in stack`() {
//        val stack = Stack()
//        stack.push(BigDecimal("10"))
//
//        AddCommand().execute(stack)
//    }

    @Test
    fun `subtract command should subtract two numbers`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("5"))

        SubtractCommand().execute(stack)

        assertEquals("5", stack.toString())
    }

    @Test
    fun `multiply command should multiple two numbers`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))

        MultiplyCommand().execute(stack)

        assertEquals("200", stack.toString())
    }

    @Test
    fun `divide command should divide two numbers`() {
        val stack = Stack()
        stack.push(BigDecimal("100"))
        stack.push(BigDecimal("5"))

        DivideCommand().execute(stack)

        assertEquals("20", stack.toString())
    }

    @Test
    fun `square root command should square root the top item`() {
        val stack = Stack()
        stack.push(BigDecimal("144"))

        SquareRootCommand().execute(stack)

        assertEquals("12", stack.toString())
    }

    // TODO Make this test work
//    @Test
//    fun `square root command should throw an exception for negative numbers`() {
//        val stack = Stack()
//        stack.push(BigDecimal("-144"))
//
//        SquareRootCommand().execute(stack)
//
//        assertEquals("12", stack.toString())
//    }

//    @Test
//    fun `undo command should undo the last action`() {
//        val stack = Stack()
//        stack.push(BigDecimal("100"))
//
//        UndoCommand().execute(stack)
//
//        assertEquals("", stack.toString())
//    }
}