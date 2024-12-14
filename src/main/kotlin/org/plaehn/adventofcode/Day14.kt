package org.plaehn.adventofcode

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.product

class Day14(
    private val robots: List<Robot>,
    private val width: Int,
    private val height: Int
) {

    fun solvePart1(): Int {

        val finalPositions: List<Coord> = robots.map { robot ->
            var position = robot.position
            repeat(100) {
                //println(position)
                position += robot.velocity
                val x = if (position.x >= 0) position.x else width + position.x
                val y = if (position.y >= 0) position.y else height + position.y
                position = Coord(x, y) % Coord(width, height)
            }
            //println(position)
            position
        }

        // println(finalPositions.sortedWith(compareBy({ it.x }, { it.y })))

        val quadrants: List<Pair<Coord, Coord>> =
            finalPositions.mapNotNull { finalPosition ->
                val x = when {
                    finalPosition.x < width / 2 -> 0
                    finalPosition.x > width / 2 -> 1
                    else -> null
                }
                val y = when {
                    finalPosition.y < height / 2 -> 0
                    finalPosition.y > height / 2 -> 1
                    else -> null
                }
                if (x == null || y == null) {
                    null
                } else {
                    Coord(x, y) to finalPosition
                }
            }
        val quads: Multimap<Coord, Coord> = ArrayListMultimap.create()
        quadrants.forEach { q ->
            quads.put(q.first, q.second)
        }
        return quads.asMap().values.map { it.size }.product()
    }

    fun solvePart1_failed(): Int {
        val finalPositions = robots.map { robot ->
            val finalPos = (robot.position + robot.velocity * 100) % (Coord(width, height))
            val x = if (finalPos.x >= 0) finalPos.x else width + finalPos.x
            val y = if (finalPos.y >= 0) finalPos.y else height + finalPos.y
            val corrected = Coord(x, y)
            println(corrected)
            corrected
        }

        println(finalPositions)

        val quadrants: List<Coord> =
            finalPositions.map { finalPosition ->
                Coord(width / (1 + finalPosition.x), height / (1 + finalPosition.y))
            }
        return quadrants
            .groupBy { it }
            .values.size
    }

    fun solvePart2(): Int {
        TODO()
    }

    companion object {
        fun fromInput(input: List<String>, width: Int, height: Int) =
            Day14(input.map { Robot.fromInput(it) }, width, height)
    }

    data class Robot(
        val position: Coord,
        val velocity: Coord
    ) {
        companion object {
            fun fromInput(input: String): Robot {
                val coords = input.toNumbers().zipWithNext { x, y -> Coord(x, y) }
                return Robot(coords.first(), coords.last())
            }

            private fun String.toNumbers(): List<Int> =
                "-?\\d+".toRegex().findAll(this).map { it.value.toInt() }.toList()
        }
    }
}



