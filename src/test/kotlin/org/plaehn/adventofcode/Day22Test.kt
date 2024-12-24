package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day22Test {

    @Test
    fun `Part 1 Test Input`() {
        val day22 = Day22(readLinesAsLongs("22_test.txt"))

        assertThat(day22.solvePart1()).isEqualTo(37327623)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day22 = Day22(readLinesAsLongs("22.txt"))

        assertThat(day22.solvePart1()).isEqualTo(20071921341)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day22 = Day22(readLinesAsLongs("22_test.txt"))

        assertThat(day22.solvePart2()).isEqualTo(-1)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day22 = Day22(readLinesAsLongs("22.txt"))

        assertThat(day22.solvePart2()).isEqualTo(-1)
    }

    private fun readLinesAsLongs(resource: String) =
        this::class.java.readLines(resource).map { it.toLong() }
}