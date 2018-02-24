import java.math.BigDecimal

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

        stack.push(secondNumber.divide(firstNumber))
    }
}

class SquareRootCommand: Command {
    override fun execute(stack: Stack) {
        stack.saveState()
        val firstNumber = stack.pop()

        val sqrRoot = Math.sqrt(firstNumber.toDouble())

        stack.push(BigDecimal(sqrRoot))
    }
}

class UndoCommand: Command {
    override fun execute(stack: Stack) {
        stack.revertState()
    }
}

//class ClearCommand: Command {
//    override fun execute(stack: Stack): Stack {
//        return Stack()
//    }
//}