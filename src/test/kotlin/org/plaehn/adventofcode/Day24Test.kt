package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.plaehn.adventofcode.common.slurp

class Day24Test {

    @Test
    fun `Part 1 Test Input`() {
        assertThat(Day24.fromInput(slurp("24_test_1.txt")).solvePart1()).isEqualTo(4)
        assertThat(Day24.fromInput(slurp("24_test_2.txt")).solvePart1()).isEqualTo(2024)
    }

    @Test
    fun `Part 1 Puzzle Input`() {
        assertThat(Day24.fromInput(slurp("24.txt")).solvePart1()).isEqualTo(48508229772400)
    }

    @Test
    @Disabled("Not really a test")
    fun `Part 2 Puzzle Input`() {
        Day24.fromInput(slurp("24_fixed.txt")).solvePart2()

        // No programmatic solution this time, instead followed this approach:
        // 1) set all x wires to 1 and all y wires to 0, so all z wires should be one apart from the last one (bit 45)
        // 2) inspected the z outputs for the first wrong bit and manually tried to identify the correct swap
        //    by looking at the generated dot graph
        // 3) repeated this step until I had found all four swaps

        // 0111111110000000001000000000000111111111111111
        // bit 15 is wrong
        // swapped qnw and z15

        // 0111111110000000001000000011111111111111111111
        // bit 20 is wrong
        // swapped cqr and z20

        // 0111111110000000000111111111111111111111111111
        // bit 27 is wrong
        // swapped ncd and nfj

        // 1000000001111111111111111111111111111111111111
        // bit 37 is wrong
        // swapped vkg and z37

        // solution: cqr,ncd,nfj,qnw,vkg,z15,z20,z37

        // command to create graph from output:
        // $ dot -Tsvg 24_part2_graph.dot > 24_part2_graph.svg
    }

    private fun slurp(resource: String) = this::class.java.slurp(resource)
}