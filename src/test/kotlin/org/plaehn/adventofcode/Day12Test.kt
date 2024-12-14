package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day12Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day12(linesFrom("12_test_1.txt")).solvePart1()).isEqualTo(140)
        assertThat(Day12(linesFrom("12_test_2.txt")).solvePart1()).isEqualTo(772)
        assertThat(Day12(linesFrom("12_test_3.txt")).solvePart1()).isEqualTo(1930)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day12(linesFrom("12.txt")).solvePart1()).isEqualTo(1437300)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day12(linesFrom("12_test.txt")).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day12(linesFrom("12.txt")).solvePart2()).isEqualTo(-1)
    }

    private fun linesFrom(resource: String) = this::class.java.readLines(resource)
}