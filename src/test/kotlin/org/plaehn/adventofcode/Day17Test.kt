package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day17Test {

    @Test
    fun `Part 1 Test Input`() {
        val day17 = Day17.fromInput(readLines("17_test.txt"))

        assertThat(day17.solvePart1()).isEqualTo("4,6,3,5,6,3,5,2,1,0")
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day17 = Day17.fromInput(readLines("17.txt"))

        assertThat(day17.solvePart1()).isEqualTo("4,3,2,6,4,5,3,2,4")
    }

    @Test
    fun `Part 2 Test Input`() {
        val day17 = Day17.fromInput(readLines("17_test.txt"))

        assertThat(day17.solvePart2()).isEqualTo("")
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day17 = Day17.fromInput(readLines("17.txt"))

        assertThat(day17.solvePart2()).isEqualTo("")
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}