package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day21Test {

    @Test
    fun `Part 1 Test Input`() {
        val day21 = Day21.fromInput(readLines("21_test.txt"))

        assertThat(day21.solvePart1()).isEqualTo(10)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day21 = Day21.fromInput(readLines("21.txt"))

        assertThat(day21.solvePart1()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day21 = Day21.fromInput(readLines("21_test.txt"))

        assertThat(day21.solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day21 = Day21.fromInput(readLines("21.txt"))

        assertThat(day21.solvePart2()).isEqualTo(-1)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}