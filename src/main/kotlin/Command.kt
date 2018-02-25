import java.math.BigDecimal
import java.math.RoundingMode

interface Command {
    fun execute(stack: Stack)
    fun throwExceptionIfNotEnoughNumbers(stack: Stack, requiredNumbers: Int) {
        if (stack.size() < requiredNumbers) throw IllegalArgumentException("Not enough numbers in stack")
    }
}

class AddCommand: Command {
    override fun execute(stack: Stack) {
        throwExceptionIfNotEnoughNumbers(stack, 2)
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        stack.push(firstNumber.add(secondNumber))
    }
}

class SubtractCommand: Command {
    override fun execute(stack: Stack) {
        throwExceptionIfNotEnoughNumbers(stack, 2)
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        stack.push(secondNumber.subtract(firstNumber))
    }
}

class MultiplyCommand: Command {
    override fun execute(stack: Stack) {
        throwExceptionIfNotEnoughNumbers(stack, 2)
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        stack.push(firstNumber.multiply(secondNumber))
    }
}

class DivideCommand: Command {
    override fun execute(stack: Stack) {
        throwExceptionIfNotEnoughNumbers(stack, 2)
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        val divisionResult = secondNumber.divide(firstNumber, 15, RoundingMode.UP)

        stack.push(divisionResult)
    }
}

class SquareRootCommand: Command {
    override fun execute(stack: Stack) {
        throwExceptionIfNotEnoughNumbers(stack, 1)
        stack.saveState()
        val firstNumber = stack.pop()

        if (firstNumber < BigDecimal.ZERO) {
            stack.revertState()
            throw IllegalArgumentException("Cannot sqrt negative numbers")
        }
        val sqrRoot = Math.sqrt(firstNumber.toDouble())

        stack.push(BigDecimal(sqrRoot).setScale(15, RoundingMode.HALF_UP))
    }
}

class UndoCommand: Command {
    override fun execute(stack: Stack) {
        stack.revertState()
    }
}

class ClearCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        stack.clear()
    }
}

class PushNewNumberCommand(private val newNumber: String): Command {
    override fun execute(stack: Stack) {
        try {
            val newNumberAsBigDecimal = BigDecimal(newNumber).setScale(15, RoundingMode.HALF_UP)
            stack.saveState()
            stack.push(newNumberAsBigDecimal)
        } catch (exception: NumberFormatException) {
            throw IllegalArgumentException("Bad operator")
        }
    }
}