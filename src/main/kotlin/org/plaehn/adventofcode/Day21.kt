package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Coord.Companion.DOWN
import org.plaehn.adventofcode.common.Coord.Companion.LEFT
import org.plaehn.adventofcode.common.Coord.Companion.RIGHT
import org.plaehn.adventofcode.common.Coord.Companion.UP
import org.plaehn.adventofcode.common.Matrix
import java.util.*

class Day21(private val codes: List<String>) {

    private val numericKeypad = Matrix.fromRows(
        listOf("789", "456", "123", "#0A").map { it.toCharArray().toList() }, '#'
    ).toMap().toMutableMap().apply { remove(Coord(0, 3)) }

    private val directionalKeypad = Matrix.fromRows(
        listOf("#^A", "<v>").map { it.toCharArray().toList() }, '#'
    ).toMap().toMutableMap().apply { remove(Coord(0, 0)) }

    private val numericPathsWithLowestCost = numericKeypad.allPathsWithLowestCost()

    private val directionalPathsWithLowestCost = directionalKeypad.allPathsWithLowestCost()

    private fun Map<Coord, Char>.allPathsWithLowestCost(): Map<Pair<Char, Char>, List<String>> =
        keys.allPairs()
            .associate {
                (getValue(it.first) to getValue(it.second)) to findLowestCostPaths(it.first, it.second)
            }

    private fun Map<Coord, Char>.findLowestCostPaths(start: Coord, end: Coord): List<String> {
        val queue = PriorityQueue<Pair<List<Coord>, Int>>(compareBy { it.second })
            .apply { add(listOf(start) to 0) }
        val seen = mutableMapOf<Coord, Int>()
        var costAtGoal: Int? = null
        val allPaths: MutableList<String> = mutableListOf()

        while (queue.isNotEmpty()) {
            val (path, cost) = queue.poll()
            val location = path.last()

            if (costAtGoal != null && cost > costAtGoal) {
                return allPaths
            } else if (path.last() == end) {
                costAtGoal = cost
                allPaths.add(path.zipWithNext().map { (from, to) -> from.diffToChar(to) }.joinToString("") + "A")
            } else if (seen.getOrDefault(location, Int.MAX_VALUE) >= cost) {
                seen[location] = cost
                location
                    .neighbors()
                    .filter { it in keys }
                    .forEach { queue.add(path + it to cost + 1) }
            }
        }
        return allPaths
    }

    private fun Coord.diffToChar(other: Coord): Char =
        when (val result = other - this) {
            UP -> '^'
            RIGHT -> '>'
            DOWN -> 'v'
            LEFT -> '<'
            else -> throw IllegalArgumentException("Invalid direction $result")
        }

    private fun <T> Collection<T>.allPairs(): List<Pair<T, T>> =
        flatMap { left ->
            map { right -> left to right }
        }

    fun solve(depth: Int): Long {

        return codes.sumOf { code -> findCost(code, depth) * code.dropLast(1).toLong() }
    }

    private fun findCost(
        code: String,
        depth: Int,
        transitions: Map<Pair<Char, Char>, List<String>> = numericPathsWithLowestCost,
        cache: MutableMap<Pair<String, Int>, Long> = mutableMapOf()
    ): Long =
        cache.getOrPut(code to depth) {
            "A$code".zipWithNext().sumOf { transition ->
                val paths: List<String> = transitions.getValue(transition)
                if (depth == 0) {
                    paths.minOf { it.length }.toLong()
                } else {
                    paths.minOf { path -> findCost(path, depth - 1, directionalPathsWithLowestCost, cache) }
                }
            }
        }
}

