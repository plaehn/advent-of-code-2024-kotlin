package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.product

class Day14(
    private val robots: List<Robot>,
    private val width: Int,
    private val height: Int
) {

    fun solvePart1(): Int =
        moveRobots(seconds = 100)
            .map { it.position }
            .toRobotsPerQuadrant()
            .product()

    private fun moveRobots(seconds: Int) =
        (1..seconds).fold(this.robots) { robots, _ ->
            robots.map { it.move(width, height) }
        }

    private fun List<Coord>.toRobotsPerQuadrant(): List<Int> =
        mapNotNull { position -> position.toQuadrant() }
            .groupBy { it }
            .map { it.value.size }

    private fun Coord.toQuadrant(): Coord? {
        return Coord(
            when {
                x < width / 2 -> 0
                x > width / 2 -> 1
                else -> return null
            },
            when {
                y < height / 2 -> 0
                y > height / 2 -> 1
                else -> return null
            }
        )
    }

    fun solvePart2(): Int {
        (0..10000).foldIndexed(this.robots) { idx, robots, _ ->
            if (robots.isPotentialXmas()) {
                robots.visualize(idx)
                return idx
            }
            robots.map { it.move(width, height) }
        }
        throw IllegalStateException()
    }

    private fun List<Robot>.isPotentialXmas() =
        this.distinctBy { it.position }.size == this.size

    private fun List<Robot>.visualize(idx: Int) {
        println("Seconds: $idx")
        println()
        val position2RobotCount: Map<Coord, Int> = this.groupBy { it.position }.mapValues { (_, value) -> value.size }
        (0..<width).forEach { x ->
            (0..<height).forEach { y ->
                val cnt = position2RobotCount.getOrDefault(Coord(x, y), 0)
                val chr = if (cnt == 0) ' ' else '*'
                print("$chr")
            }
            println()
        }
        println()
        println()
    }


    companion object {
        fun fromInput(input: List<String>, width: Int, height: Int) =
            Day14(input.map { Robot.fromInput(it) }, width, height)
    }

    data class Robot(
        val position: Coord,
        val velocity: Coord
    ) {

        fun move(width: Int, height: Int): Robot {
            val position = position + velocity
            val x = if (position.x >= 0) position.x else width + position.x
            val y = if (position.y >= 0) position.y else height + position.y
            return Robot(position = Coord(x, y) % Coord(width, height), velocity)
        }

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





