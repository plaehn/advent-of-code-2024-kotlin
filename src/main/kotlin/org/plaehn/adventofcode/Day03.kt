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
        var multiply = true
        return "do\\(\\)|don't\\(\\)|mul\\((\\d{1,3}),(\\d{1,3})\\)"
            .toRegex()
            .findAll(input)
            .map { op ->
                when (op.value) {
                    "do()" -> {
                        multiply = true
                        0
                    }

                    "don't()" -> {
                        multiply = false
                        0
                    }

                    else -> {
                        if (multiply) {
                            op.groupValues[1].toInt() * op.groupValues[2].toInt()
                        } else {
                            0
                        }
                    }
                }
            }
            .sum()
    }
}


