package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.tokenize

class Day11(input: String) {

    private val stones = input.tokenize().map { it.toLong() }
    private val state2Count: MutableMap<State, Long> = mutableMapOf()

    fun solve(numberOfBlinks: Int): Long =
        stones.sumOf { stone ->
            countStones(State(numberOfBlinks, stone))
        }

    private fun countStones(state: State): Long {
        if (state.remainingBlinks == 0) return 1
        if (state2Count.containsKey(state)) return state2Count.getValue(state)

        val stoneAsString = state.stone.toString()
        val stoneLength = stoneAsString.length
        val count = when {
            state.stone == 0L -> countStones(state.nextState(1L))

            stoneLength % 2 == 0 ->
                countStones(state.nextState(stoneAsString.take(stoneLength / 2).toLong())) +
                        countStones(state.nextState(stoneAsString.takeLast(stoneLength / 2).toLong()))

            else -> countStones(state.nextState(state.stone * 2024))
        }
        state2Count[state] = count
        return count
    }

    data class State(
        val remainingBlinks: Int,
        val stone: Long
    ) {
        fun nextState(stone: Long) =
            State(remainingBlinks - 1, stone)
    }
}