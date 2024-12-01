package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.tokenize
import org.plaehn.adventofcode.common.transpose
import kotlin.math.abs

class Day01(
    private val lines: List<String>
) {
    fun solvePart1(): Int =
        lines.toNumberLists()
            .map { it.sorted() }
            .transpose()
            .sumOf { abs(it.reduce(Int::minus)) }

    fun solvePart2(): Int =
        lines.toNumberLists()
            .run {
                first().sumOf { leftNum -> leftNum * last().count { rightNum -> rightNum == leftNum } }
            }

    private fun List<String>.toNumberLists() =
        this.map { line -> line.tokenize().map { it.toInt() } }
            .transpose()
}
