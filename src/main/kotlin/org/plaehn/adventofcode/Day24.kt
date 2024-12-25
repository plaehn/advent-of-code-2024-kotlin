package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.chunkByBlankLines
import org.plaehn.adventofcode.common.tokenize

class Day24(
    private val wire2ValueMap: Map<String, Int>,
    private val gates: List<Gate>
) {

    fun solvePart1(): Int {
        println(wire2ValueMap)
        println(gates)
        return 0
    }


    fun solvePart2(): Int {
        return 0
    }

    data class Gate(
        val lhs: String,
        val operator: Operator,
        val rhs: String,
        val output: String
    ) {
        companion object {
            fun fromInput(input: String): Gate {
                val (lhs, operator, rhs, _, result) = input.tokenize()
                return Gate(lhs, Operator.fromInput(operator), rhs, result)
            }
        }
    }

    enum class Operator {
        AND,
        OR,
        XOR;

        companion object {
            fun fromInput(input: String): Operator =
                entries.find { it.name == input }
                    ?: throw IllegalArgumentException("Unknown operator $input")
        }
    }

    companion object {

        fun fromInput(input: String): Day24 {
            val chunks = input.chunkByBlankLines()
            val wire2ValueMap = chunks.first().associate {
                val (wire, value) = it.split(": ")
                wire to value.toInt()
            }
            val gates = chunks.last().map { Gate.fromInput(it) }
            return Day24(wire2ValueMap, gates)
        }
    }
}

