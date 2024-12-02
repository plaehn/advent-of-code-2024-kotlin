package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day02Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.readLines("02_test.txt")

        assertThat(Day02(lines).solvePart1()).isEqualTo(2)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.readLines("02.txt")

        assertThat(Day02(lines).solvePart1()).isEqualTo(559)
    }

    @Test
    fun `Part 2 Test Input`() {
        val lines = this::class.java.readLines("02_test.txt")

        assertThat(Day02(lines).solvePart2()).isEqualTo(0)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val lines = this::class.java.readLines("02.txt")

        assertThat(Day02(lines).solvePart2()).isEqualTo(0)
    }
}