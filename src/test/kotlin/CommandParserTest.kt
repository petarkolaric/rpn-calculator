import org.junit.Assert.assertEquals
import org.junit.Test

class CommandParserTest {
    @Test
    fun `should parse add command`() {
        val result = CommandParser.parseCommandString("+")

        assertEquals(AddCommand::class, result::class)
    }

    @Test
    fun `should parse subtract command`() {
        val result = CommandParser.parseCommandString("-")

        assertEquals(SubtractCommand::class, result::class)
    }

    @Test
    fun `should parse multiply command`() {
        val result = CommandParser.parseCommandString("*")

        assertEquals(MultiplyCommand::class, result::class)
    }

    @Test
    fun `should parse divide command`() {
        val result = CommandParser.parseCommandString("/")

        assertEquals(DivideCommand::class, result::class)
    }

    @Test
    fun `should parse square root command`() {
        val result = CommandParser.parseCommandString("sqrt")

        assertEquals(SquareRootCommand::class, result::class)
    }

    @Test
    fun `should parse undo command`() {
        val result = CommandParser.parseCommandString("undo")

        assertEquals(UndoCommand::class, result::class)
    }

    @Test
    fun `should parse clear command`() {
        val result = CommandParser.parseCommandString("clear")

        assertEquals(ClearCommand::class, result::class)
    }

    @Test
    fun `should parse new number command`() {
        val result = CommandParser.parseCommandString("-13213.5435")

        assertEquals(PushNewNumberCommand::class, result::class)
    }
}