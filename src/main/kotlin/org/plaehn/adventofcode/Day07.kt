package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.concat

typealias LongOperation = (Long, Long) -> Long

class Day07(
    private val equations: List<Equation>
) {
    fun solvePart1(): Long =
        solve(listOf(Long::plus, Long::times))

    fun solvePart2(): Long =
        solve(listOf(Long::plus, Long::times, Long::concat))

    private fun solve(operations: List<LongOperation>): Long =
        equations.sumOf { equation -> equation.solve(operations) }

    data class Equation(
        val testValue: Long,
        val operands: List<Long>
    ) {

        fun solve(operations: List<LongOperation>): Long =
            operands
                .drop(1)
                .fold(listOf(operands.first())) { acc, operand ->
                    operations
                        .flatMap { operation -> acc.map { operation(it, operand) } }
                        .filter { it <= testValue }
                }
                .find { it == testValue } ?: 0

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