package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.chunkByBlankLines
import org.plaehn.adventofcode.common.tokenize

class Day24(
    private val initialWire2ValueMap: Map<String, Int>,
    private val initialGates: List<Gate>
) {

    fun solvePart1(): Long {
        val wire2ValueMap = initialWire2ValueMap.toMutableMap()
        var gates = initialGates
        while (gates.isNotEmpty()) {
            gates = gates.mapNotNull { gate ->
                if (gate.lhs in wire2ValueMap && gate.rhs in wire2ValueMap) {
                    wire2ValueMap[gate.output] = gate.compute(wire2ValueMap[gate.lhs]!!, wire2ValueMap[gate.rhs]!!)
                    null
                } else {
                    gate
                }
            }
        }
        val keys = wire2ValueMap
            .keys
            .filter { it.startsWith("z") }
            .sortedDescending()
        val binary = keys.map { wire2ValueMap[it] }.joinToString("")
        return binary.toLong(2)
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
        fun compute(lhsValue: Int, rhsValue: Int): Int =
            when (operator) {
                Operator.AND -> lhsValue and rhsValue
                Operator.OR -> lhsValue or rhsValue
                Operator.XOR -> lhsValue xor rhsValue
            }

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

