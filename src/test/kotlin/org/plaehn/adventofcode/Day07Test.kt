package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.readLines

class Day07Test {

    @Test
    fun `Part 1 Test Input`() {
        val lines = this::class.java.readLines("07_test.txt")

        assertThat(Day07.fromInput(lines).solve()).isEqualTo(3749)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        val lines = this::class.java.readLines("07.txt")

        assertThat(Day07.fromInput(lines).solve()).isEqualTo(5512534574980)
    }

    @Test
    fun `Part 2 Test Input`() {
        val lines = this::class.java.readLines("07_test.txt")

        assertThat(Day07.fromInput(lines, useConcat = true).solve()).isEqualTo(11387)
    }

    @Test
    fun `Part 2 Puzzle Input`() {
        val lines = this::class.java.readLines("07.txt")

        assertThat(Day07.fromInput(lines, useConcat = true).solve()).isEqualTo(328790210468594)
    }
}