import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Rule
import org.junit.Test
import java.math.BigDecimal
import org.junit.rules.ExpectedException



class CommandTest {

    @Rule @JvmField
    var thrown: ExpectedException = ExpectedException.none()

    @Test
    fun `add command should add two numbers`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("5"))

        AddCommand().execute(stack)

        assertEquals("15", stack.toString())
    }

    @Test(expected = InsufficientStackSizeException::class)
    fun `should throw exception if not enough numbers to add in stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))

        AddCommand().execute(stack)
    }

    @Test(expected = InsufficientStackSizeException::class)
    fun `should throw exception if not enough numbers to subtract in stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))

        SubtractCommand().execute(stack)
    }

    @Test(expected = InsufficientStackSizeException::class)
    fun `should throw exception if not enough numbers to multiply in stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))

        MultiplyCommand().execute(stack)
    }

    @Test(expected = InsufficientStackSizeException::class)
    fun `should throw exception if not enough numbers to divide in stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))

        DivideCommand().execute(stack)
    }

    @Test(expected = InsufficientStackSizeException::class)
    fun `should throw exception if not enough numbers to sqrt in stack`() {
        val stack = Stack()

        SquareRootCommand().execute(stack)
    }

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

    @Test
    fun `square root command should throw an exception for negative numbers`() {
        val stack = Stack()
        stack.push(BigDecimal("-144"))
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("Cannot sqrt negative numbers")

        SquareRootCommand().execute(stack)
    }

    @Test
    fun `square root command should not change stack state after failing to sqrt negative`() {
        val stack = Stack()
        stack.push(BigDecimal("-144"))

        try {
            SquareRootCommand().execute(stack)
            fail("Should never reach here")
        } catch (exception: IllegalArgumentException) {

        }

        assertEquals("-144", stack.toString())
    }

    @Test
    fun `undo command should undo square root`() {
        val stack = Stack()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("100"))

        SquareRootCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("50 100", stack.toString())
    }

    @Test
    fun `undo command should undo add`() {
        val stack = Stack()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("100"))

        AddCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("50 100", stack.toString())
    }

    @Test
    fun `undo command should undo subtract`() {
        val stack = Stack()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("100"))

        SubtractCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("50 100", stack.toString())
    }

    @Test
    fun `undo command should undo multiply`() {
        val stack = Stack()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("100"))

        MultiplyCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("50 100", stack.toString())
    }

    @Test
    fun `undo command should undo divide`() {
        val stack = Stack()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("100"))

        DivideCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("50 100", stack.toString())
    }

    @Test
    fun `should be able to undo multiple times`() {
        val stack = Stack()
        stack.push(BigDecimal("50"))
        stack.push(BigDecimal("100"))
        stack.push(BigDecimal("200"))
        stack.push(BigDecimal("300"))

        AddCommand().execute(stack)
        AddCommand().execute(stack)
        AddCommand().execute(stack)
        UndoCommand().execute(stack)
        UndoCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("50 100 200 300", stack.toString())
    }

    @Test
    fun `clear command should clear stack`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))

        ClearCommand().execute(stack)

        assertEquals("", stack.toString())
    }

    @Test
    fun `should be able to undo clear command`() {
        val stack = Stack()
        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("20"))

        ClearCommand().execute(stack)
        UndoCommand().execute(stack)

        assertEquals("10 20", stack.toString())
    }

    @Test
    fun `should be able to push new number to stack`() {
        val stack = Stack()

        PushNewNumberCommand("10.56").execute(stack)

        assertEquals("10.56", stack.toString())
    }

    @Test
    fun `should be able to undo pushing new number`() {
        val stack = Stack()

        PushNewNumberCommand("10.56").execute(stack)
        UndoCommand().execute(stack)

        assertEquals("", stack.toString())
    }

    @Test
    fun `should only divide to 15 decimal places of precision`() {
        val stack = Stack()

        stack.push(BigDecimal("10"))
        stack.push(BigDecimal("13"))

        DivideCommand().execute(stack)
        val result = stack.pop()

        println(result.toString())

        assertEquals(BigDecimal("0.769230769230770"), result)
    }

    @Test
    fun `should only square root to 15 decimal places of precision`() {
        val stack = Stack()

        stack.push(BigDecimal("1323832813"))

        SquareRootCommand().execute(stack)
        val result = stack.pop()

        assertEquals(BigDecimal("36384.513367640356591"), result)
    }

    @Test
    fun `should only save new numbers to 15 places of precision`() {
        val stack = Stack()

        PushNewNumberCommand("12345.12345678901234567890").execute(stack)

        val savedNumber = stack.pop()

        assertEquals(BigDecimal("12345.123456789012346"), savedNumber)
    }

    @Test
    fun `should only print numbers to 10 places of precision`() {
        val stack = Stack()

        PushNewNumberCommand("12345.12345678911234567890").execute(stack)

        assertEquals("12345.1234567891", stack.toString())
    }

    @Test
    fun `should throw exception when trying to add bad new number`() {
        val stack = Stack()
        thrown.expect(IllegalArgumentException::class.java)
        thrown.expectMessage("Bad operator")

        PushNewNumberCommand("batman").execute(stack)
    }
}