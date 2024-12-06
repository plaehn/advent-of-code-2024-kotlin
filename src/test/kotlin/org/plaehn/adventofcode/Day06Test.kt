package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day06Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.readLines("06_test.txt")

        assertThat(Day06(lines).solvePart1()).isEqualTo(41)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.readLines("06.txt")

        assertThat(Day06(lines).solvePart1()).isEqualTo(5101)
    }

    @Test
    fun `Part 2 Test Input`() {
        val lines = this::class.java.readLines("06_test.txt")

        assertThat(Day06(lines).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val lines = this::class.java.readLines("06.txt")

        assertThat(Day06(lines).solvePart2()).isEqualTo(-1)
    }
}