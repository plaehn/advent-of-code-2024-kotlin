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

    private val matrix = createMatrix()
    private val guardStartingPosition = findGuardPosition(matrix)

    private fun createMatrix() = Matrix.fromRows(lines.map { it.toCharArray().toList() }, ' ')

    fun solvePart1(): Int =
        findGuardPositions(guardStartingPosition, matrix)
            .visited
            .distinctBy { it.position }
            .size

    private fun findGuardPositions(
        guardStartingPosition: Coord,
        matrix: Matrix<Char>,
        throwOnLoop: Boolean = false
    ): GuardPositions {
        var guardPosition = guardStartingPosition
        var guardDirection = UP
        val guardPositions = GuardPositions()

        guardPositions.visited.add(GuardPositionAndDirection(guardPosition, guardDirection))

        while (true) {
            val inFront = matrix.getOrDefaultValue(guardPosition + guardDirection)
            when (inFront) {
                '.', '^' -> {
                    guardPosition += guardDirection
                    guardPositions.potentialObstructionPlaces.add(guardPosition)
                    val isNew = guardPositions.visited.add(GuardPositionAndDirection(guardPosition, guardDirection))
                    if (!isNew && throwOnLoop) {
                        throw LoopException()
                    }
                }

                '#' -> guardDirection = turnRight(guardDirection)
                ' ' -> break
            }
        }
        return guardPositions
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

    fun solvePart2(): Int =
        (findGuardPositions(guardStartingPosition, matrix).potentialObstructionPlaces - guardStartingPosition)
            .count { potentialObstructionPlace ->
                val matrix = createMatrix()
                matrix.set(potentialObstructionPlace, '#')
                try {
                    findGuardPositions(guardStartingPosition, matrix, throwOnLoop = true)
                    false
                } catch (exx: LoopException) {
                    true
                }
            }

    data class GuardPositions(
        val visited: MutableSet<GuardPositionAndDirection> = mutableSetOf(),
        val potentialObstructionPlaces: MutableSet<Coord> = mutableSetOf()
    )

    data class GuardPositionAndDirection(
        val position: Coord,
        val direction: Coord
    )

    class LoopException : RuntimeException()
}




