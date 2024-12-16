package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day16Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day16.fromInput(readLines("16_test.txt")).solvePart1()).isEqualTo(7036)
    }

    // this takes almost 3 minutes
    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day16.fromInput(readLines("16.txt")).solvePart1()).isEqualTo(109516)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day16.fromInput(readLines("16_test.txt")).solvePart2()).isEqualTo(45)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day16.fromInput(readLines("16.txt")).solvePart2()).isEqualTo(-1)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}