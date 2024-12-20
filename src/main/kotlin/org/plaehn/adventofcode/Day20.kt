package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix

class Day20(private val matrix: Matrix<Char>) {

    fun solvePart1(minimalSaving: Int): Int =
        findPath()
            .computeSavings()
            .count { saving -> saving >= minimalSaving }

    private fun findPath(): Map<Coord, Int> {
        val coord2PathLength = mutableMapOf<Coord, Int>()
        var currentPathLength = 0
        var current = matrix.findAll('S').first()
        val end = matrix.findAll('E').first()
        while (current != end) {
            coord2PathLength[current] = currentPathLength++
            val next = matrix
                .neighbors(current)
                .firstOrNull { neighbor ->
                    neighbor !in coord2PathLength && matrix[neighbor] != '#'
                } ?: throw IllegalStateException()
            current = next
        }
        coord2PathLength[end] = currentPathLength
        return coord2PathLength
    }

    private fun Map<Coord, Int>.computeSavings() =
        flatMap { (coord, length) ->
            matrix
                .neighbors(coord)
                .filter { neighbor -> matrix[neighbor] == '#' }
                .map { wall ->
                    val behindWall = coord + ((wall - coord) * 2)
                    val saving = if (matrix[behindWall] != '#') {
                        getOrDefault(behindWall, length) - length - 2
                    } else {
                        0
                    }
                    saving
                }
        }

    fun solvePart2(): Int {
        return 0
    }

    companion object {
        fun fromInput(lines: List<String>): Day20 {
            return Day20(
                Matrix.fromRows(
                    lines
                        .map { it.toCharArray().toList() }
                        .addWallToAvoidBoundsChecks(),
                    '.'
                )
            )
        }

        private fun List<List<Char>>.addWallToAvoidBoundsChecks(): List<List<Char>> {
            val wallRow: List<List<Char>> = listOf(List(size = first().size + 2) { '#' })
            return wallRow + map { listOf('#') + it + listOf('#') } + wallRow
        }
    }
}

