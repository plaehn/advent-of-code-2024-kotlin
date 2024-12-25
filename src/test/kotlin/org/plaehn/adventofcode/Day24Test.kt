package org.plaehn.adventofcode

import assertk.assertThat
import assertk.assertions.isEqualTo
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
    fun `Part 2 Puzzle Input`() {
        Day24.fromInput(slurp("24_fixed.txt")).solvePart2()

        // 0111111110000000001000000000000111111111111111
        // bit 15 is wrong
        // swapped qnw and z15

        // 0111111110000000001000000011111111111111111111
        // bit 20 is wrong

        // all wrong:
        //cqr,msn,ncd,nfj,qkm,qnw,y14,z15
        //cqr,dnn,msn,ncd,nfj,qkm,qnw,z15
        //cqr,dnn,msn,ncd,nfj,qkc,qnw,z15
    }

    private fun slurp(resource: String) = this::class.java.slurp(resource)
}