package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day16Test {

    @Test
    fun `Part 1 Test Input`() {
        val day16 = Day16.fromInput(readLines("16_test.txt"))

        assertThat(day16.solvePart1()).isEqualTo(7036)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day16 = Day16.fromInput(readLines("16.txt"))

        assertThat(day16.solvePart1()).isEqualTo(109516)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day16 = Day16.fromInput(readLines("16_test.txt"))

        assertThat(day16.solvePart2()).isEqualTo(45)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day16 = Day16.fromInput(readLines("16.txt"))

        assertThat(day16.solvePart2()).isEqualTo(568)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}