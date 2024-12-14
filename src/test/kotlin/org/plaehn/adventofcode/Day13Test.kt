package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day13Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day13.fromInput(slurp("13_test.txt")).solvePart1()).isEqualTo(480)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day13.fromInput(slurp("13.txt")).solvePart1()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day13.fromInput(slurp("13_test.txt")).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day13.fromInput(slurp("13.txt")).solvePart2()).isEqualTo(-1)
    }

    private fun slurp(resource: String) = this::class.java.slurp(resource)
}