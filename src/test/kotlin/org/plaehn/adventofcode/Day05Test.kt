package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day05Test {

    @Test
    fun `Part 1 Test Input`() {
        val input = this::class.java.slurp("05_test.txt")

        assertThat(Day05(input).solvePart1()).isEqualTo(143)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val input = this::class.java.slurp("05.txt")

        assertThat(Day05(input).solvePart1()).isEqualTo(6260)
    }

    @Test
    fun `Part 2 Test Input`() {
        val input = this::class.java.slurp("05_test.txt")

        assertThat(Day05(input).solvePart2()).isEqualTo(123)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val input = this::class.java.slurp("05.txt")

        assertThat(Day05(input).solvePart2()).isEqualTo(5346)
    }
}