package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.tokenize
import kotlin.math.abs
import kotlin.math.sign

class Day02(
    private val lines: List<String>
) {
    fun solvePart1() =
        lines.toReports().count { it.isSafe() }

    private fun List<Int>.isSafe(): Boolean {
        val diffs = this.zipWithNext().map { (prev, current) -> current - prev }
        val signs = diffs.map { sign(it) }
        return (signs.all { it == 1 } || signs.all { it == -1 }) && diffs.all { abs(it) in 1..3 }
    }

    private fun sign(value: Int): Int =
        sign(value.toFloat()).toInt()

    fun solvePart2(): Int {
        TODO()
    }

    private fun List<String>.toReports() =
        this.map { line -> line.tokenize().map { it.toInt() } }
}

