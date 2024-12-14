package org.plaehn.adventofcode.common

import com.google.common.collect.Sets

data class Coord(val x: Int, val y: Int, val z: Int = 0) {

    override fun toString() = "($x,$y)"

    operator fun plus(summand: Coord) = Coord(x + summand.x, y + summand.y, z + summand.z)

    operator fun minus(subtrahend: Coord) = Coord(x - subtrahend.x, y - subtrahend.y, z - subtrahend.z)

    operator fun times(factor: Coord): Coord = Coord(x * factor.x, y * factor.y, z * factor.z)

    operator fun times(factor: Int): Coord = Coord(x * factor, y * factor, z * factor)

    operator fun rem(coord: Coord): Coord = Coord(x % coord.x, y % coord.y)

    fun neighbors(includeDiagonals: Boolean = false, dimensions: Int = 2) =
        neighborOffsets(includeDiagonals, dimensions)
            .map { this + it }

    private fun neighborOffsets(includeDiagonals: Boolean, dimensions: Int) =
        Sets.cartesianProduct(List(dimensions) { (-1..1).toSet() })
            .map { fromList(it) }
            .filter { !it.isCenter() }
            .filter { offset -> includeDiagonals || 1 == listOf(offset.x, offset.y, offset.z).count { it != 0 } }

    private fun isCenter() = x == 0 && y == 0 && z == 0

    companion object {
        val UP = Coord(0, -1)
        val DOWN = Coord(0, 1)
        val LEFT = Coord(-1, 0)
        val RIGHT = Coord(1, 0)

        fun fromList(input: List<Int>) =
            Coord(x = input[0], y = input[1], z = input.getOrElse(2) { 0 })
    }
}