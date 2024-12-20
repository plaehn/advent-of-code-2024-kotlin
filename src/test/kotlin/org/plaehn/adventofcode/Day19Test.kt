package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day19Test {

    @Test
    fun `Part 1 Test Input`() {
        val day19 = Day19.fromInput(readLines("19_test.txt"))

        assertThat(day19.solvePart1()).isEqualTo(6)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day19 = Day19.fromInput(readLines("19.txt"))

        assertThat(day19.solvePart1()).isEqualTo(293)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day19 = Day19.fromInput(readLines("19_test.txt"))

        assertThat(day19.solvePart2()).isEqualTo(16)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day19 = Day19.fromInput(readLines("19.txt"))

        assertThat(day19.solvePart2()).isEqualTo(623924810770264)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}