import java.math.BigDecimal
import java.text.DecimalFormat

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

    fun size(): Int {
        return stackContents.size
    }

    override fun toString(): String {
        var outputString = ""
        val decimalFormat = DecimalFormat()
        decimalFormat.maximumFractionDigits = 10
        decimalFormat.minimumFractionDigits = 0
        decimalFormat.isGroupingUsed = false
        stackContents.forEach {
            outputString += decimalFormat.format(it) + " "
        }
        return outputString.trim()
    }
}