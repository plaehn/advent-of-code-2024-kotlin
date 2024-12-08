package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day08Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.readLines("08_test.txt")

        assertThat(Day08.fromInput(lines).solvePart1()).isEqualTo(14)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.readLines("08.txt")

        assertThat(Day08.fromInput(lines).solvePart1()).isEqualTo(376)
    }

    @Test
    fun `Part 2 Test Input`() {
        val lines = this::class.java.readLines("08_test.txt")

        assertThat(Day08.fromInput(lines).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val lines = this::class.java.readLines("08.txt")

        assertThat(Day08.fromInput(lines).solvePart2()).isEqualTo(-1)
    }
}