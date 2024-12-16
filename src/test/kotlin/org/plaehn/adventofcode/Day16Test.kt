package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day16Test {

    @Test
    fun `Part 1 and 2 Test Input`() {
        val day16 = Day16.fromInput(readLines("16_test.txt"))
        val paths = day16.findBestPathsWithCost()

        assertThat(day16.solvePart1(paths)).isEqualTo(7036)
        assertThat(day16.solvePart2(paths)).isEqualTo(45)
    }

    // this takes almost 3 minutes
    @Test
    fun `Part 1 and 2 Puzzle Input`() {
        val day16 = Day16.fromInput(readLines("16.txt"))
        val paths = day16.findBestPathsWithCost()

        assertThat(day16.solvePart1(paths)).isEqualTo(109516)
        assertThat(day16.solvePart2(paths)).isEqualTo(568)

        assertThat(Day16.fromInput(readLines("16.txt")).solvePart1(paths)).isEqualTo(109516)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}