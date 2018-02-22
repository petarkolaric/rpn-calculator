import java.math.BigDecimal

class Stack() {

    private val stackContents = mutableListOf<BigDecimal>()

    fun push(number: BigDecimal) {
        stackContents.add(number)
    }

    fun pop(): BigDecimal {
        return stackContents.removeAt(0)
    }

    override fun toString(): String {
        var outputString = ""
        stackContents.forEach {
            outputString += it.toString() + " "
        }
        return outputString.trim()
    }

}