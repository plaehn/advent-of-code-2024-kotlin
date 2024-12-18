package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day03Test {

    @Test
    fun `Part 1 Test Input`() {
        val input = this::class.java.slurp("03_test.txt")

        assertThat(Day03(input).solvePart1()).isEqualTo(161)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val input = this::class.java.slurp("03.txt")

        assertThat(Day03(input).solvePart1()).isEqualTo(165225049)
    }

    @Test
    fun `Part 2 Test Input`() {
        val input = this::class.java.slurp("03_part2_test.txt")

        assertThat(Day03(input).solvePart2()).isEqualTo(48)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val input = this::class.java.slurp("03.txt")

        assertThat(Day03(input).solvePart2()).isEqualTo(108830766)
    }
}