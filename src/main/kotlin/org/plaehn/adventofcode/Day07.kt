package org.plaehn.adventofcode

class Day07(
    private val equations: List<Equation>,
    private val useConcat: Boolean
) {

    fun solve(): Long =
        equations.sumOf { equation -> equation.solve(useConcat) }

    data class Equation(
        val testValue: Long,
        val operands: List<Long>
    ) {
        fun solve(useConcat: Boolean): Long =
            compute(operands.first(), operands.drop(1), useConcat)

        private fun compute(result: Long, remainingOperands: List<Long>, useConcat: Boolean): Long {
            if (remainingOperands.isEmpty()) return if (result == testValue) testValue else 0
            if (result >= testValue) return result

            val next = remainingOperands.first()

            return when {
                testValue == compute(result + next, remainingOperands.drop(1), useConcat) -> testValue
                testValue == compute(result * next, remainingOperands.drop(1), useConcat) -> testValue
                useConcat && testValue == compute(result concat next, remainingOperands.drop(1), useConcat) -> testValue
                else -> 0
            }
        }

        companion object {
            fun fromInput(input: String): Equation {
                val (testValue, operands) = input.split(":")
                return Equation(
                    testValue = testValue.trim().toLong(),
                    operands = operands.split(" ").filter { it.isNotBlank() }.map { it.trim().toLong() }
                )
            }
        }
    }

    companion object {
        fun fromInput(lines: List<String>, useConcat: Boolean = false) =
            Day07(lines.map { Equation.fromInput(it) }, useConcat)
    }
}

private infix fun Long.concat(next: Long) =
    (this.toString() + next.toString()).toLong()




