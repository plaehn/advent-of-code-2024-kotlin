package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day14Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day14.fromInput(readLines("14_test.txt"), 11, 7).solvePart1()).isEqualTo(12)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day14.fromInput(readLines("14.txt"), 101, 103).solvePart1()).isEqualTo(222062148)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day14.fromInput(readLines("14_test.txt"), 11, 7).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day14.fromInput(readLines("14.txt"), 101, 103).solvePart2()).isEqualTo(-1)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}