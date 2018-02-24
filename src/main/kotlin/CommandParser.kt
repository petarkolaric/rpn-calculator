import java.math.BigDecimal

class CommandParser {
    companion object {
        fun parseCommandString(inputString: String): Command {
            return when (inputString) {
                "+" -> AddCommand()
                "-" -> SubtractCommand()
                "*" -> MultiplyCommand()
                "/" -> DivideCommand()
                "sqrt" -> SquareRootCommand()
                "undo" -> UndoCommand()
                "clear" -> ClearCommand()
                else -> PushNewNumberCommand(BigDecimal(inputString))
            }
        }
    }
}