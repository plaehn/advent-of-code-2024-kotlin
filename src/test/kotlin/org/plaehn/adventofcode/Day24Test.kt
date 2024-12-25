package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day24Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day24.fromInput(slurp("24_test_1.txt")).solvePart1()).isEqualTo(4)
        assertThat(Day24.fromInput(slurp("24_test_2.txt")).solvePart1()).isEqualTo(2024)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day24.fromInput(slurp("24.txt")).solvePart1()).isEqualTo(48508229772400)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day24.fromInput(slurp("24_test_1.txt")).solvePart2()).isEqualTo(-1)
        assertThat(Day24.fromInput(slurp("24_test_2.txt")).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day24.fromInput(slurp("24.txt")).solvePart2()).isEqualTo(-1)
    }

    private fun slurp(resource: String) = this::class.java.slurp(resource)
}