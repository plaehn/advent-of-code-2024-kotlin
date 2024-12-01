package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.tokenize
import org.plaehn.adventofcode.common.transpose
import kotlin.math.abs

class Day01(
    private val lines: List<String>
) {
    fun solvePart1(): Int =
        lines.map { line -> line.tokenize().map { it.toInt() } }
            .transpose()
            .map { it.sorted() }
            .transpose()
            .sumOf { abs(it.reduce(Int::minus)) }
            
    fun solvePart2(): Int {
        TODO("Not yet implemented")
    }

}
