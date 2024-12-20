package org.plaehn.adventofcode

import org.plaehn.adventofcode.Day15.Move.LEFT
import org.plaehn.adventofcode.Day15.Move.RIGHT
import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.chunkByBlankLines

class Day15(
    private val warehouse: Matrix<Char>,
    private val moves: List<Move>
) {

    fun solvePart1(): Int =
        warehouse
            .doMovements()
            .findAll('O')
            .sumOf { coord -> coord.y * 100 + coord.x }


    fun solvePart2(): Int =
        warehouse
            .doMovements()
            .findAll('[')
            .sumOf { coord -> coord.y * 100 + coord.x }

    private fun Matrix<Char>.doMovements(): Matrix<Char> {
        val start = findAll('@').first()
        var place = start
        moves.forEach { direction ->
            val next = place + direction.offset
            when (this[next]) {
                in BOX_CHARS -> {
                    push(next, direction)?.let { moves ->
                        moves.forEach { (from, to) ->
                            this[to] = this[from]
                            this[from] = '.'
                        }
                        place = next
                    }
                }

                !in "#" -> {
                    place = next
                }
            }
        }
        return this
    }

    private fun Matrix<Char>.push(position: Coord, move: Move): List<Pair<Coord, Coord>>? {
        val safePushes = mutableListOf<Pair<Coord, Coord>>()
        val queue = mutableListOf(position)
        val seen = mutableSetOf<Coord>()

        while (queue.isNotEmpty()) {
            val thisPosition = queue.removeFirst()
            if (thisPosition !in seen) {
                seen += thisPosition
                if (!move.isHorizontally()) {
                    when (this[thisPosition]) {
                        ']' -> queue.add(thisPosition + LEFT.offset)
                        '[' -> queue.add(thisPosition + RIGHT.offset)
                    }
                }
                val nextPosition = thisPosition + move.offset
                when (this[nextPosition]) {
                    '#' -> return null
                    in BOX_CHARS -> queue.add(nextPosition)
                }
                safePushes.add(thisPosition to nextPosition)
            }
        }
        return safePushes.reversed()
    }


    companion object {

        private val BOX_CHARS = setOf('O', '[', ']')

        fun fromInput(input: String, widen: Boolean = false): Day15 {
            val chunks = input.chunkByBlankLines()
            val matrixRows = chunks.first()
                .map { it.toCharArray().toList() }
                .map { row ->
                    row.flatMap { chr ->
                        when {
                            !widen -> listOf(chr)
                            chr == '#' -> listOf('#', '#')
                            chr == 'O' -> listOf('[', ']')
                            chr == '.' -> listOf('.', '.')
                            chr == '@' -> listOf('@', '.')
                            else -> throw IllegalArgumentException("Unknown warehouse char $chr")
                        }
                    }
                }

            val matrix = Matrix.fromRows(matrixRows, '.')
            val moves = chunks.last()
                .joinToString("")
                .filterNot { it == '\n' }
                .map { Move.fromInput(it) }
            return Day15(matrix, moves)
        }
    }

    enum class Move(val offset: Coord, private val chr: Char) {
        UP(Coord(0, -1), '^'),
        DOWN(Coord(0, 1), 'v'),
        LEFT(Coord(-1, 0), '<'),
        RIGHT(Coord(1, 0), '>');

        fun isHorizontally() = this == LEFT || this == RIGHT

        companion object {
            fun fromInput(input: Char): Move =
                entries.firstOrNull { input == it.chr }
                    ?: throw IllegalArgumentException("Move $input not known")
        }
    }
}

