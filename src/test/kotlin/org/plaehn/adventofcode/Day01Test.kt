package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day01Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.readLines("01_test.txt")

        assertThat(Day01(lines).solvePart1()).isEqualTo(11)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.readLines("01.txt")

        assertThat(Day01(lines).solvePart1()).isEqualTo(2970687)
    }
}