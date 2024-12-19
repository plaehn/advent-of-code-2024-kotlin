package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day18Test {

    @Test
    fun `Part 1 Test Input`() {
        val day18 = Day18.fromInput(input = readLines("18_test.txt"), dimension = 7, take = 12)

        assertThat(day18.solvePart1()).isEqualTo(22)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day18 = Day18.fromInput(input = readLines("18.txt"), dimension = 71, take = 1024)

        assertThat(day18.solvePart1()).isEqualTo(308)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day18 = Day18.fromInput(input = readLines("18_test.txt"), dimension = 7, take = 12)

        assertThat(day18.solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day18 = Day18.fromInput(input = readLines("18.txt"), dimension = 70, take = 1024)

        assertThat(day18.solvePart2()).isEqualTo(-1)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}