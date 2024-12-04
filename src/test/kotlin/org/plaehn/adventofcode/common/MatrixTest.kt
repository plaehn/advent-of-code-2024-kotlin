package org.plaehn.adventofcode.common

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.hasMessage
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import org.junit.jupiter.api.Test

class MatrixTest {

    @Test
    fun `Given non-square matrix when transpose45DegreeClockwise then exception is thrown`() {
        val matrix = Matrix.fromRows(
            listOf(
                listOf(0, 1, 2),
                listOf(3, 4, 5)
            ),
            0
        )

        assertFailure { matrix.transpose45DegreeClockwise() }
            .isInstanceOf(IllegalArgumentException::class)
            .hasMessage("Works only if width equals height")
    }

    @Test
    fun `Given square matrix when transpose45DegreeClockwise then correctly transposed matrix is returned`() {
        val matrix = Matrix.fromRows(
            listOf(
                listOf(2, 5, 7, 2),
                listOf(9, 1, 4, 3),
                listOf(5, 8, 2, 3),
                listOf(6, 4, 6, 3)
            ),
            0
        )

        val transposedMatrix = matrix.transpose45DegreeClockwise()

        assertThat(transposedMatrix.rows()).isEqualTo(
            listOf(
                listOf(0, 0, 0, 2, 0, 0, 0),
                listOf(0, 0, 9, 0, 5, 0, 0),
                listOf(0, 5, 0, 1, 0, 7, 0),
                listOf(6, 0, 8, 0, 4, 0, 2),
                listOf(0, 4, 0, 2, 0, 3, 0),
                listOf(0, 0, 6, 0, 3, 0, 0),
                listOf(0, 0, 0, 3, 0, 0, 0)
            )
        )
    }
}