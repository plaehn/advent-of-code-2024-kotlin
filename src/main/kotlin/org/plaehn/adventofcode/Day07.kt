package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.concat

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

        private fun compute(result: Long, operands: List<Long>, useConcat: Boolean): Long =
            if (operands.isEmpty() || result >= testValue)
                result
            else
                listOf(Long::plus, Long::times, Long::concat)
                    .filter { useConcat || it != Long::concat }
                    .map { operation -> compute(operation(result, operands.first()), operands.drop(1), useConcat) }
                    .find { it == testValue }
                    ?: 0

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