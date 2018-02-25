import kotlin.system.exitProcess

class RPNCalculator {
    companion object {
        fun parseAndExecuteLine(nextLine: String?, stack: Stack): String {
            if (nextLine == null) {
                exitProcess(0)
            } else {
                var commandPosition = 0
                nextLine.trim()
                        .split(' ')
                        .filter { it.isNotEmpty() }
                        .forEach { inputCommandString ->
                            val command = CommandParser.parseCommandString(inputCommandString)
                            try {
                                command.execute(stack)
                                commandPosition += inputCommandString.length
                            } catch (exception: InsufficientStackSizeException) {
                                return "operator $inputCommandString (position: $commandPosition): insufficient parameters" +
                                        "\nstack: $stack"
                            } catch (exception: IllegalArgumentException) {
                                return "cannot apply operator '$inputCommandString' to stack" +
                                        "\nstack: $stack"
                            }
                        }
            }
            return "stack: $stack"
        }
    }
}