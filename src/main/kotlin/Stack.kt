import java.math.BigDecimal

class Stack {

    private val stackContents = mutableListOf<BigDecimal>()

    fun push(number: BigDecimal) {
        stackContents.add(number)
    }

    fun pop(): BigDecimal {
        if (stackContents.isEmpty()) {
            throw IllegalStateException("No items in stack to pop")
        }
        return stackContents.removeAt(stackContents.lastIndex)
    }

    override fun toString(): String {
        var outputString = ""
        stackContents.forEach {
            outputString += it.toString() + " "
        }
        return outputString.trim()
    }

}