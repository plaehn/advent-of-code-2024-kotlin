package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.tokenize

class Day11(private val input: String) {

    fun solvePart1(): Int {
        var stones = input.tokenize().map { it.toLong() }
        repeat(25) {
            stones = blink(stones)
        }
        return stones.size
    }

    private fun blink(stones: List<Long>): List<Long> =
        stones.flatMap { stone ->
            val stoneAsString = stone.toString()
            val stoneLength = stoneAsString.length
            when {
                stone == 0L -> listOf(1L)

                stoneLength % 2 == 0 -> listOf(
                    stoneAsString.take(stoneLength / 2).toLong(),
                    stoneAsString.takeLast(stoneLength / 2).toLong(),
                )

                else -> listOf(stone * 2024)
            }
        }

    fun solvePart2(): Int {
        return 0
    }
}