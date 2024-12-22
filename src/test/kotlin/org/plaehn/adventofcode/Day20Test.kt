package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day20Test {

    @Test
    fun `Part 1 Test Input`() {
        val day20 = Day20.fromInput(readLines("20_test.txt"))

        assertThat(day20.solve(minimalSaving = 10, maxCheatLength = 2)).isEqualTo(10)
        assertThat(day20.solve(minimalSaving = 20, maxCheatLength = 2)).isEqualTo(5)
        assertThat(day20.solve(minimalSaving = 40, maxCheatLength = 2)).isEqualTo(2)
        assertThat(day20.solve(minimalSaving = 60, maxCheatLength = 2)).isEqualTo(1)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day20 = Day20.fromInput(readLines("20.txt"))

        assertThat(day20.solve(minimalSaving = 100, maxCheatLength = 2)).isEqualTo(1293)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day20 = Day20.fromInput(readLines("20_test.txt"))

        assertThat(day20.solve(minimalSaving = 76, maxCheatLength = 20)).isEqualTo(3)
        assertThat(day20.solve(minimalSaving = 72, maxCheatLength = 20)).isEqualTo(29)
        assertThat(day20.solve(minimalSaving = 70, maxCheatLength = 20)).isEqualTo(41)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day20 = Day20.fromInput(readLines("20.txt"))

        assertThat(day20.solve(minimalSaving = 100, maxCheatLength = 20)).isEqualTo(977747)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}