package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.chunkByBlankLines

class Day13(private val clawMachines: List<ClawMachine>) {

    fun solvePart1(): Int {
        println(clawMachines)
        return 0
    }

    fun solvePart2(): Int {
        return 0
    }

    companion object {
        fun fromInput(input: String) =
            Day13(
                input
                    .chunkByBlankLines()
                    .map { chunk -> ClawMachine.fromInput(chunk) }
            )
    }

    data class ClawMachine(
        val buttonAOffset: Coord,
        val buttonBOffset: Coord,
        val prize: Coord
    ) {
        companion object {
            fun fromInput(lines: List<String>): ClawMachine {
                require(lines.size == 3)
                val coords = lines.map { line ->
                    val numbers = line.toNumbers()
                    require(numbers.size == 2)
                    Coord(numbers.first(), numbers.last())
                }
                return ClawMachine(
                    buttonAOffset = coords[0],
                    buttonBOffset = coords[1],
                    prize = coords[2]
                )
            }

            private fun String.toNumbers(): List<Int> =
                "\\d+".toRegex().findAll(this).map { it.value.toInt() }.toList()
        }
    }
}


