fun main(args: Array<String>) {
    val stack = Stack()
    var nextLine: String?
    println("Please enter your commands:")
    while (true) {
        nextLine = readLine()
        val output = RPNCalculator.parseAndExecuteLine(nextLine, stack)
        println(output)
    }
}