package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day15Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day15.fromInput(slurp("15_test_1.txt")).solvePart1()).isEqualTo(2028)
        assertThat(Day15.fromInput(slurp("15_test_2.txt")).solvePart1()).isEqualTo(10092)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day15.fromInput(slurp("15.txt")).solvePart1()).isEqualTo(1430536)
    }

    @Test
    fun `Part 2 Test Input`() {
        assertThat(Day15.fromInput(slurp("15_test_1.txt"), widen = true).solvePart2()).isEqualTo(1751)
        assertThat(Day15.fromInput(slurp("15_test_3.txt"), widen = true).solvePart2()).isEqualTo(618)
        assertThat(Day15.fromInput(slurp("15_test_2.txt"), widen = true).solvePart2()).isEqualTo(9021)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        assertThat(Day15.fromInput(slurp("15.txt"), widen = true).solvePart2()).isEqualTo(1452348)
    }

    private fun slurp(resource: String) = this::class.java.slurp(resource)
}