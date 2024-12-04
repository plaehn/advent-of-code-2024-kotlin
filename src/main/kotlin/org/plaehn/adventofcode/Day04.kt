package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix

class Day04(
    lines: List<String>
) {

    private val matrix = Matrix.fromRows(lines.map { it.toCharArray().toList() }, '.')

    fun solvePart1(): Int {

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

    fun solvePart2(): Int =
        matrix
            .toMap()
            .filter { (coord, element) -> isXmas(element, coord) }
            .count()

    private fun isXmas(element: Char, coord: Coord) =
        element == 'A'
            && topLeft2BottomRightDiagonal(coord) == setOf('M', 'S')
            && bottomLeft2TopRightDiagonal(coord) == setOf('M', 'S')

    private fun topLeft2BottomRightDiagonal(coord: Coord) =
        setOf(
            coord.neighbor(offset = Coord(-1, -1)),
            coord.neighbor(offset = Coord(1, 1))
        )

    private fun bottomLeft2TopRightDiagonal(coord: Coord) =
        setOf(
            coord.neighbor(offset = Coord(-1, 1)),
            coord.neighbor(offset = Coord(1, -1))
        )

    private fun Coord.neighbor(offset: Coord) = matrix.getOrDefaultValue(this + offset)

    companion object {
        private val XMAS_REGEX = "XMAS".toRegex()
    }
}


