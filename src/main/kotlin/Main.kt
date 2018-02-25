import kotlin.system.exitProcess

fun main(args: Array<String>) {
    RPNCalculator.run()
}

class RPNCalculator {
    companion object {
        fun run() {
            val stack = Stack()
            var nextLine: String?
            println("Please enter your commands:")
            while (true) {
                nextLine = readLine()
                if (nextLine == null) {
                    exitProcess(0)
                } else {
                    nextLine.split(' ').forEach {
                        inputCommandString ->
                            val command = CommandParser.parseCommandString(inputCommandString)
                            command.execute(stack)
                    }
                }
                println("stack: $stack")
            }
        }
    }
}