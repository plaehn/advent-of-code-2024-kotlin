package org.plaehn.adventofcode.common

import kotlin.math.abs


data class Matrix<T>(
    private val matrix: List<MutableList<T>>,
    private val defaultValue: T
) {

    operator fun get(coord: Coord) = this[coord.y][coord.x]

    fun getOrDefaultValue(coord: Coord) =
        try {
            this[coord.y][coord.x]
        } catch (exx: IndexOutOfBoundsException) {
            defaultValue
        }

    operator fun set(coord: Coord, value: T) {
        this[coord.y][coord.x] = value
    }

    operator fun get(rowNumber: Int): MutableList<T> = matrix[rowNumber]

    fun values() = matrix.flatten()

    fun rows(): List<MutableList<T>> = matrix.toList()

    fun columns() = transpose().rows()

    fun toMap(): Map<Coord, T> =
        sequence {
            for (y in 0 until height()) {
                for (x in 0 until width()) {
                    yield(Coord(x, y) to matrix[y][x])
                }
            }
        }.toMap()

    fun neighbors(coord: Coord, includeDiagonals: Boolean = false) =
        coord
            .neighbors(includeDiagonals)
            .filter { isInsideBounds(it) }

    private fun isInsideBounds(coord: Coord) = coord.y in 0 until height() && coord.x in 0 until width()

    fun width() = matrix.first().size

    fun height() = matrix.size

    private fun transpose(): Matrix<T> {
        val transposed = MutableList(width()) { MutableList(height()) { defaultValue } }
        for (i in 0 until height()) {
            for (j in 0 until width()) {
                transposed[j][i] = matrix[i][j]
            }
        }
        return Matrix(transposed, defaultValue)
    }

    fun transpose45DegreeClockwise(): Matrix<T> {
        require(width() == height()) { "Works only if width equals height" }

        val newHeight = 2 * height() - 1
        val newRows = (0..<newHeight).map { newRowIndex -> createNewRow(newRowIndex) }
        return fromRows(newRows, defaultValue)
    }

    private fun createNewRow(newRowIndex: Int): List<T> =
        buildList {
            padWithDefaultElement(paddingLength = abs(width() - newRowIndex - 1))

            var first = true
            for (y in 0..<height()) {
                for (x in 0..<width()) {
                    if (isOnDiagonal(x, y, newRowIndex)) {
                        if (!first) add(defaultValue) else first = false
                        add(get(Coord(y, x)))
                    }
                }
            }

            padWithDefaultElement(paddingLength = abs(width() - newRowIndex - 1))
        }

    private fun isOnDiagonal(x: Int, y: Int, newRowIndex: Int) = x + y == newRowIndex

    private fun MutableList<T>.padWithDefaultElement(paddingLength: Int) {
        repeat(paddingLength) {
            add(defaultValue)
        }
    }

    override fun toString() =
        matrix
            .joinToString(separator = "\n") { row ->
                row.joinToString(separator = "") {
                    it.toString()
                }
            }

    companion object {

        fun <T> fromRows(rows: List<List<T>>, defaultValue: T) =
            Matrix(rows.map { it.toMutableList() }, defaultValue)
    }
}


