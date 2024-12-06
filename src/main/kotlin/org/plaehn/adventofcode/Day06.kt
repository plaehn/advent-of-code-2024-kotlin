package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Coord.Companion.DOWN
import org.plaehn.adventofcode.common.Coord.Companion.LEFT
import org.plaehn.adventofcode.common.Coord.Companion.RIGHT
import org.plaehn.adventofcode.common.Coord.Companion.UP
import org.plaehn.adventofcode.common.Matrix


class Day06(
    private val lines: List<String>
) {

    fun solvePart1(): Int {
        val matrix = Matrix.fromRows(lines.map { it.toCharArray().toList() }, ' ')

        var guardPosition = findGuardPosition(matrix)
        var guardDirection = UP
        val visitedPositions = mutableSetOf<Coord>()

        while (true) {
            visitedPositions.add(guardPosition)
            val inFront = matrix.getOrDefaultValue(guardPosition + guardDirection)
            when (inFront) {
                '.', '^' -> guardPosition += guardDirection
                '#' -> guardDirection = turnRight(guardDirection)
                ' ' -> break
            }
        }
        return visitedPositions.size
    }

    private fun findGuardPosition(matrix: Matrix<Char>) =
        matrix.toMap().firstNotNullOf { (coord, elem) ->
            if (elem == '^') coord else null
        }

    private fun turnRight(guardDirection: Coord) =
        when (guardDirection) {
            UP -> RIGHT
            RIGHT -> DOWN
            DOWN -> LEFT
            else -> UP
        }

    fun solvePart2(): Int {
        return -1
    }
}




