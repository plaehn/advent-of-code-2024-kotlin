package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day25Test {

    @Test
    fun `Part 1 Test Input`() {
        val day25 = Day25.fromInput(slurp("25_test.txt"))

        assertThat(day25.solvePart1()).isEqualTo(3)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day25 = Day25.fromInput(slurp("25.txt"))

        assertThat(day25.solvePart1()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day25 = Day25.fromInput(slurp("25_test.txt"))

        assertThat(day25.solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day25 = Day25.fromInput(slurp("25.txt"))

        assertThat(day25.solvePart2()).isEqualTo(-1)
    }

    private fun slurp(resource: String) = this::class.java.slurp(resource)
}