import java.math.BigDecimal

class Stack {

    private var stackContents = mutableListOf<BigDecimal>()
    private val previousStackContents = mutableListOf<MutableList<BigDecimal>>()

    fun push(number: BigDecimal) {
        stackContents.add(number)
    }

    fun pop(): BigDecimal {
        if (stackContents.isEmpty()) {
            throw IllegalStateException("No items in stack to pop")
        }
        return stackContents.removeAt(stackContents.lastIndex)
    }

    fun clear() {
        stackContents = mutableListOf()
    }

    fun revertState() {
        if (previousStackContents.isEmpty()) {
            throw IllegalStateException("No operations to undo")
        }
        stackContents = previousStackContents.removeAt(previousStackContents.lastIndex)
    }

    fun saveState() {
        previousStackContents.add(stackContents.toMutableList())
    }

    override fun toString(): String {
        var outputString = ""
        stackContents.forEach {
            outputString += it.toString() + " "
        }
        return outputString.trim()
    }
}