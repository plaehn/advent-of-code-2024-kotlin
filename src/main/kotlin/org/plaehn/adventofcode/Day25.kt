package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.chunkByBlankLines

class Day25(private val locks: List<List<Int>>, private val keys: List<List<Int>>) {

    fun solvePart1(): Int =
        locks.sumOf { lock -> lock.countMatchingKeys() }

    private fun List<Int>.countMatchingKeys(): Int =
        keys.count { key -> key.matches(this) }

    private fun List<Int>.matches(key: List<Int>) =
        zip(key).all { it.first + it.second <= 5 }

    companion object {
        fun fromInput(input: String): Day25 {
            val locks = input.chunkByBlankLines()
                .filter { chunk -> chunk.first().all { it == '#' } }
                .map { it.drop(1) }
                .map { chunk -> chunk.map { it.toCharArray().toList() } }
                .map { chunk -> Matrix.fromRows(chunk, ' ').columns() }
                .map { columns -> columns.map { column -> column.count { it == '#' } } }

            val keys = input.chunkByBlankLines()
                .filter { chunk -> chunk.last().all { it == '#' } }
                .map { it.dropLast(1) }
                .map { chunk -> chunk.map { it.toCharArray().toList() } }
                .map { chunk -> Matrix.fromRows(chunk, ' ').columns() }
                .map { columns -> columns.map { column -> column.count { it == '#' } } }
            return Day25(locks, keys)
        }
    }
}

