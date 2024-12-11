package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day11(TEST_INPUT).solve(25)).isEqualTo(55312)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day11(PUZZLE_INPUT).solve(25)).isEqualTo(217812)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day11(TEST_INPUT).solve(75)).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day11(PUZZLE_INPUT).solve(75)).isEqualTo(-1)
    }

    companion object {
        private const val TEST_INPUT = "125 17"
        private const val PUZZLE_INPUT = "510613 358 84 40702 4373582 2 0 1584"
    }
}