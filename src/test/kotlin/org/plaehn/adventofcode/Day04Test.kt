package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day04Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.readLines("04_test.txt")

        assertThat(Day04(lines).solvePart1()).isEqualTo(18)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.readLines("04.txt")

        assertThat(Day04(lines).solvePart1()).isEqualTo(2718)
    }

    @Test
    fun `Part 2 Test Input`() {
        val lines = this::class.java.readLines("04_test.txt")

        assertThat(Day04(lines).solvePart2()).isEqualTo(9)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val lines = this::class.java.readLines("04.txt")

        assertThat(Day04(lines).solvePart2()).isEqualTo(2046)
    }
}