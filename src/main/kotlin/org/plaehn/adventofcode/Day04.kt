package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Matrix

class Day04(
    private val lines: List<String>
) {
    fun solvePart1(): Int {
        val matrix = Matrix.fromRows(lines.map { it.toCharArray().toList() }, '.')

        val candidateStrings = buildList {
            addAll(matrix.collectRows())
            addAll(matrix.collectColumns())
            addAll(matrix.collectBottomLeft2TopRightDiagonals())
            addAll(matrix.collectTopLeft2BottomRightDiagonals())
        }.toMutableList()

        candidateStrings += candidateStrings.map { it.reversed() }

        return candidateStrings.sumOf { XMAS_REGEX.findAll(it).count() }
    }

    private fun Matrix<Char>.collectRows() =
        rows().map { it.joinToString("") }

    private fun Matrix<Char>.collectColumns() =
        columns().map { it.joinToString("") }

    private fun Matrix<Char>.collectBottomLeft2TopRightDiagonals() =
        transpose45DegreeClockwise().rows().map { row ->
            row.filter { it != '.' }.joinToString("")
        }

    private fun Matrix<Char>.collectTopLeft2BottomRightDiagonals() =
        transpose45DegreeClockwise().columns().map { column ->
            column.filter { it != '.' }.joinToString("")
        }

    fun solvePart2() =
        0

    companion object {
        private val XMAS_REGEX = "XMAS".toRegex()
    }
}


