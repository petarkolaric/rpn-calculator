import org.junit.Test
import org.junit.Assert.assertEquals

class RPNCalculatorTest {
    @Test
    fun `calculator should return stack after adding numbers`() {
        val stack = Stack()
        val response = RPNCalculator.parseAndExecuteLine("5 2", stack)

        assertEquals("stack: 5 2", response)
    }

    @Test
    fun `calculator should return error message when insufficient numbers`() {
        val stack = Stack()
        val response = RPNCalculator.parseAndExecuteLine("5 2 / / /", stack)
        val expectedResponse = "operator / (position: 3): insufficient parameters\n" +
                                "stack: 2.5"

        assertEquals(expectedResponse, response)
    }

    @Test
    fun `calculator should return error message when passing unrecognised command`() {
        val stack = Stack()
        val response = RPNCalculator.parseAndExecuteLine("superman", stack)
        val expectedResponse = "cannot apply operator 'superman' to stack\n" +
                                "stack: "

        assertEquals(expectedResponse, response)
    }
}