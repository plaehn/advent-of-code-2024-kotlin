package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day09Test {

    @Test
    fun `Part 1 Small Test Input`() {
        val input = "12345"

        assertThat(Day09(input).solvePart1()).isEqualTo(60)
    }

    @Test
    fun `Part 1 Test Input`() {
        val input = this::class.java.slurp("09_test.txt")

        assertThat(Day09(input).solvePart1()).isEqualTo(1928)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val input = this::class.java.slurp("09.txt")

        assertThat(Day09(input).solvePart1()).isEqualTo(6301895872542)
    }

    @Test
    fun `Part 2 Test Input`() {
        val input = this::class.java.slurp("09_test.txt")

        assertThat(Day09(input).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val input = this::class.java.slurp("09.txt")

        assertThat(Day09(input).solvePart2()).isEqualTo(-1)
    }
}