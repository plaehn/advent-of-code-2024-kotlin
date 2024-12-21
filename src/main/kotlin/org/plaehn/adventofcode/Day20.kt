package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix

class Day20(private val matrix: Matrix<Char>) {
    private val path = findPath()

    fun solve(minimalSaving: Int, maxCheatLength: Int): Int =
        path.indices.sumOf { start ->
            (start + minimalSaving..path.lastIndex).count { end ->
                val distance = path[start].manhattanDistanceTo(path[end])
                distance <= maxCheatLength && distance <= end - start - minimalSaving
            }
        }

    private fun findPath(): List<Coord> {
        val path = mutableListOf<Coord>()
        var current = matrix.findAll('S').first()
        val end = matrix.findAll('E').first()
        while (current != end) {
            path.add(current)
            val next = matrix
                .neighbors(current)
                .firstOrNull { neighbor ->
                    neighbor !in path && matrix[neighbor] != '#'
                } ?: throw IllegalStateException()
            current = next
        }
        path.add(end)
        return path
    }

    companion object {
        fun fromInput(lines: List<String>) =
            Day20(Matrix.fromRows(lines.map { it.toCharArray().toList() }, '.'))
    }
}

