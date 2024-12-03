package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day03Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.slurp("03_test.txt")

        assertThat(Day03(lines).solvePart1()).isEqualTo(161)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.slurp("03.txt")

        assertThat(Day03(lines).solvePart1()).isEqualTo(165225049)
    }

    @Test
    fun `Part 2 Test Input`() {
        val lines = this::class.java.slurp("03_test.txt")

        assertThat(Day03(lines).solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val lines = this::class.java.slurp("03.txt")

        assertThat(Day03(lines).solvePart2()).isEqualTo(-1)
    }
}