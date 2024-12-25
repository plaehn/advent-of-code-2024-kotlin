package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day23Test {

    @Test
    fun `Part 1 Test Input`() {
        val day23 = Day23(readLines("23_test.txt"))

        assertThat(day23.solvePart1()).isEqualTo(7)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val day23 = Day23(readLines("23.txt"))

        assertThat(day23.solvePart1()).isEqualTo(1054)
    }

    @Test
    fun `Part 2 Test Input`() {
        val day23 = Day23(readLines("23_test.txt"))

        assertThat(day23.solvePart2()).isEqualTo("co,de,ka,ta")
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val day23 = Day23(readLines("23.txt"))

        assertThat(day23.solvePart2()).isEqualTo("ch,cz,di,gb,ht,ku,lu,tw,vf,vt,wo,xz,zk")
    }

    private fun readLines(resource: String) = this::class.java.readLines(resource)
}