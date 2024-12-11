package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.tokenize

class Day11(input: String) {

    private val stones = input.tokenize().map { it.toLong() }
    private val seen: MutableMap<Pair<Int, Long>, Long> = mutableMapOf()

    fun solve(numberOfBlinks: Int): Long =
        stones.sumOf { stone ->
            countStones(numberOfBlinks, stone)
        }

    private fun countStones(blinks: Int, stone: Long): Long {
        if (blinks == 0) return 1
        if (seen.containsKey(blinks to stone)) return seen.getValue(blinks to stone)

        val stoneAsString = stone.toString()
        val stoneLength = stoneAsString.length
        val remainingBlinks = blinks - 1
        val count = when {
            stone == 0L -> countStones(remainingBlinks, 1L)

            stoneLength % 2 == 0 ->
                countStones(remainingBlinks, stoneAsString.take(stoneLength / 2).toLong()) +
                        countStones(remainingBlinks, stoneAsString.takeLast(stoneLength / 2).toLong())

            else -> countStones(remainingBlinks, stone * 2024)
        }
        seen[blinks to stone] = count
        return count
    }
}