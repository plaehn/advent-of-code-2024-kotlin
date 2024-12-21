package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day20Test {

    @Test
    fun `Part 1 Test Input`() {
        val day20 = Day20.fromInput(readLines("20_test.txt"))

        assertThat(day20.solvePart1(minimalSaving = 10)).isEqualTo(10)
        assertThat(day20.solvePart1(minimalSaving = 20)).isEqualTo(5)
        assertThat(day20.solvePart1(minimalSaving = 40)).isEqualTo(2)
        assertThat(day20.solvePart1(minimalSaving = 60)).isEqualTo(1)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day20 = Day20.fromInput(readLines("20.txt"))

        assertThat(day20.solvePart1(minimalSaving = 100)).isEqualTo(1293)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day20 = Day20.fromInput(readLines("20_test.txt"))

        assertThat(day20.solvePart2(minimalSaving = 76)).isEqualTo(3)
        assertThat(day20.solvePart2(minimalSaving = 72)).isEqualTo(29)
        assertThat(day20.solvePart2(minimalSaving = 70)).isEqualTo(41)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day20 = Day20.fromInput(readLines("20.txt"))

        assertThat(day20.solvePart2(minimalSaving = 100)).isEqualTo(-1)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}