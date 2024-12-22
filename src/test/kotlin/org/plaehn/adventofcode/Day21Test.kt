package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day21Test {

    @Test
    fun `Part 1 Test Input`() {
        val day21 = Day21(readLines("21_test.txt"))

        assertThat(day21.solve(depth = 2)).isEqualTo(126384)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day21 = Day21(readLines("21.txt"))

        assertThat(day21.solve(depth = 2)).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day21 = Day21(readLines("21_test.txt"))

        assertThat(day21.solve(depth = 25)).isEqualTo(154115708116294)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day21 = Day21(readLines("21.txt"))

        assertThat(day21.solve(depth = 25)).isEqualTo(223902935165512)
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}