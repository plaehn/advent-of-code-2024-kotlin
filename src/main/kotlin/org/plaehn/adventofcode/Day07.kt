package org.plaehn.adventofcode

class Day07(
    private val equations: List<Equation>
) {

    fun solvePart1(): Long =
        equations.sumOf { equation -> equation.solve() }

    fun solvePart2(): Long =
        equations.sumOf { equation -> equation.solve() }

    data class Equation(
        val testValue: Long,
        val operands: List<Long>
    ) {
        fun solve(): Long =
            compute(operands.first(), operands.drop(1))

        private fun compute(result: Long, remainingOperands: List<Long>): Long {
            if (remainingOperands.isEmpty()) return if (result == testValue) testValue else 0
            if (result >= testValue) return result

            val next = remainingOperands.first()

            return when (testValue) {
                compute(result + next, remainingOperands.drop(1)) -> testValue
                compute(result * next, remainingOperands.drop(1)) -> testValue
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
        fun fromInput(lines: List<String>) =
            Day07(lines.map { Equation.fromInput(it) })
    }
}




