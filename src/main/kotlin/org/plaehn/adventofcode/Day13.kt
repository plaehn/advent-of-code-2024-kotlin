package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.chunkByBlankLines
import org.plaehn.adventofcode.common.toInts

class Day13(private val clawMachines: List<ClawMachine>) {

    fun solvePart1(): Long =
        clawMachines
            .sumOf { clawMachine -> clawMachine.minimumNumberOfTokensToWin() }

    fun solvePart2(): Long =
        clawMachines
            .map { ClawMachine(it.a, it.b, it.p.first + 10000000000000 to it.p.second + 10000000000000) }
            .sumOf { clawMachine -> clawMachine.minimumNumberOfTokensToWin() }

    companion object {
        fun fromInput(input: String) =
            Day13(
                input
                    .chunkByBlankLines()
                    .map { chunk -> ClawMachine.fromInput(chunk) }
            )
    }

    data class ClawMachine(
        val a: Pair<Long, Long>,
        val b: Pair<Long, Long>,
        val p: Pair<Long, Long>
    ) {
        fun minimumNumberOfTokensToWin(): Long {
            // apply Cramer's rule, cf. https://en.wikipedia.org/wiki/Cramer%27s_rule
            val divisor = a.first * b.second - a.second * b.first
            val buttonAPresses = (p.first * b.second - p.second * b.first) / divisor
            val buttonBPresses = (a.first * p.second - a.second * p.first) / divisor

            return if (
                a.first * buttonAPresses + b.first * buttonBPresses == p.first &&
                a.second * buttonAPresses + b.second * buttonBPresses == p.second
            ) {
                buttonAPresses * 3L + buttonBPresses
            } else {
                0
            }
        }

        companion object {
            fun fromInput(lines: List<String>): ClawMachine {
                require(lines.size == 3)
                val pairs = lines.map { line ->
                    val numbers = line.toInts().map { it.toLong() }
                    require(numbers.size == 2)
                    numbers.first() to numbers.last()
                }
                return ClawMachine(
                    a = pairs[0],
                    b = pairs[1],
                    p = pairs[2]
                )
            }
        }
    }
}


