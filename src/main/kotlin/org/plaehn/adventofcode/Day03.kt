package org.plaehn.adventofcode

class Day03(
    private val input: String
) {
    fun solvePart1(): Int =
        "mul\\((\\d{1,3}),(\\d{1,3})\\)"
            .toRegex()
            .findAll(input)
            .map { mul -> mul.groupValues[1].toInt() * mul.groupValues[2].toInt() }
            .sum()

    fun solvePart2(): Int {
        TODO()
    }
}


