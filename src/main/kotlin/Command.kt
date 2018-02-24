import java.math.BigDecimal
import java.math.RoundingMode

interface Command {
    fun execute(stack: Stack)
}

class AddCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        stack.push(firstNumber.add(secondNumber))
    }
}

class SubtractCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        stack.push(secondNumber.subtract(firstNumber))
    }
}

class MultiplyCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        stack.push(firstNumber.multiply(secondNumber))
    }
}

class DivideCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        val firstNumber = stack.pop()
        val secondNumber = stack.pop()

        val divisionResult = secondNumber.divide(firstNumber, 15, RoundingMode.UP)

        stack.push(divisionResult)
    }
}

class SquareRootCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        val firstNumber = stack.pop()

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

class PushNewNumberCommand(private val newNumber: BigDecimal): Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        stack.push(newNumber.setScale(15, RoundingMode.HALF_UP))
    }
}