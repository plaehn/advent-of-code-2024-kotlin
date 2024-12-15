package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.chunkByBlankLines

class Day15(
    private val warehouse: Matrix<Char>,
    private val moves: List<Move>
) {

    fun solvePart1(): Int {
        var robot = findRobot()
        moves.forEach { move ->
//            println(warehouse)
//            println()
//            println(move)
//            println()
            robot = moveRobot(robot, move)
        }
        return warehouse.toMap().map { (coord, elem) ->
            if (elem == 'O') {
                coord.y * 100 + coord.x
            } else {
                0
            }
        }.sum()
    }

    private fun findRobot() =
        warehouse.toMap().filter { (_, chr) ->
            chr == '@'
        }.keys.first()

    private fun moveRobot(robot: Coord, move: Move): Coord {
        val newPosition = robot + move.offset
        return when (warehouse[newPosition]) {
            '#' -> robot

            '.' -> {
                warehouse.swap(robot, newPosition)
                newPosition
            }

            'O' -> {
                var firstWallOrEmpty = newPosition
                while (warehouse[firstWallOrEmpty] == 'O') {
                    firstWallOrEmpty += move.offset
                }
                when (warehouse[firstWallOrEmpty]) {
                    '.' -> {
                        while (firstWallOrEmpty != robot) {
                            warehouse.swap(firstWallOrEmpty, firstWallOrEmpty - move.offset)
                            firstWallOrEmpty -= move.offset
                        }
                        newPosition
                    }

                    '#' -> robot
                    else -> throw IllegalStateException()
                }
            }

            else -> throw IllegalStateException()
        }
    }

    private fun Matrix<Char>.swap(first: Coord, second: Coord) {
        val tmp = this[first]
        this[first] = this[second]
        this[second] = tmp
    }

    fun solvePart2(): Int {
        return 0
    }

    companion object {
        fun fromInput(input: String): Day15 {
            val chunks = input.chunkByBlankLines()
            val matrix = Matrix.fromRows(chunks.first().map { it.toCharArray().toList() }, '.')
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

        companion object {
            fun fromInput(input: Char): Move =
                entries.firstOrNull { input == it.chr }
                    ?: throw IllegalArgumentException("Move $input not known")
        }
    }
}

