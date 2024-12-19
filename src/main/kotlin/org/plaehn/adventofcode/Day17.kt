package org.plaehn.adventofcode

import com.google.common.math.IntMath.pow
import org.plaehn.adventofcode.common.toInts

class Day17(
    private val initialRegisterValues: List<Long>,
    private val program: List<Int>
) {

    fun solvePart1(): String {
        var state = ComputerState(
            registerA = initialRegisterValues[0],
            registerB = initialRegisterValues[1],
            registerC = initialRegisterValues[2],
            instructionPointer = 0,
            emptyList()
        )
        while (!state.isHalted()) {
            val (opcode, operand) = program.subList(state.instructionPointer, state.instructionPointer + 2)
            val instruction = Instruction.fromOpcode(opcode)
            state = instruction.computeNextState(state, instruction, operand)
        }
        return state.output.joinToString(",")
    }

    private fun ComputerState.isHalted() = instructionPointer >= program.size

    fun solvePart2(): Long {
        //var registerAValue = 2147000000 // then got an overflow
        var registerAValue = 2145386496L
        while (true) {
            var state = ComputerState(
                registerA = registerAValue,
                registerB = initialRegisterValues[1],
                registerC = initialRegisterValues[2],
                instructionPointer = 0,
                emptyList()
            )
            if (registerAValue % 100000000L == 0L) println("registerAValue: $registerAValue")
            while (!state.isHalted() && state.output.isPrefixOf(program) && state.output != program) {
                val (opcode, operand) = program.subList(state.instructionPointer, state.instructionPointer + 2)
                val instruction = Instruction.fromOpcode(opcode)
                state = instruction.computeNextState(state, instruction, operand)
                //println(state)
            }
            if (state.output == program) break
            registerAValue += 2097152
        }
        return registerAValue
    }

    private fun List<Int>.isPrefixOf(program: List<Int>): Boolean =
        program.take(this.size) == this

    data class ComputerState(
        val registerA: Long,
        val registerB: Long,
        val registerC: Long,
        val instructionPointer: Int,
        val output: List<Int>
    )

    enum class Instruction(val opcode: Int) {
        ADV(0),
        BXL(1),
        BST(2),
        JNZ(3),
        BXC(4),
        OUT(5),
        BDV(6),
        CDV(7);

        fun computeNextState(
            state: ComputerState,
            instruction: Instruction,
            operand: Int
        ): ComputerState =
            when (instruction) {
                ADV ->
                    state.copy(
                        registerA = state.registerA / pow(2, operand.toComboOperand(state).toInt()),
                        instructionPointer = state.instructionPointer + 2
                    )

                BDV ->
                    state.copy(
                        registerB = state.registerA / pow(2, operand.toComboOperand(state).toInt()),
                        instructionPointer = state.instructionPointer + 2
                    )

                CDV ->
                    state.copy(
                        registerC = state.registerA / pow(2, operand.toComboOperand(state).toInt()),
                        instructionPointer = state.instructionPointer + 2
                    )

                BXL ->
                    state.copy(
                        registerB = state.registerB xor operand.toLong(),
                        instructionPointer = state.instructionPointer + 2
                    )

                BST ->
                    state.copy(
                        registerB = operand.toComboOperand(state) % 8,
                        instructionPointer = state.instructionPointer + 2
                    )

                JNZ ->
                    if (state.registerA == 0L) {
                        state.copy(
                            instructionPointer = state.instructionPointer + 2
                        )
                    } else {
                        state.copy(
                            instructionPointer = operand
                        )
                    }

                BXC ->
                    state.copy(
                        registerB = state.registerB xor state.registerC,
                        instructionPointer = state.instructionPointer + 2
                    )

                OUT ->
                    state.copy(
                        output = state.output + listOf(operand.toComboOperand(state) % 8).map { it.toInt() },
                        instructionPointer = state.instructionPointer + 2
                    )
            }

        private fun Int.toComboOperand(state: ComputerState): Long =
            when {
                this <= 3 -> this.toLong()
                this == 4 -> state.registerA
                this == 5 -> state.registerB
                this == 6 -> state.registerC
                else -> throw IllegalArgumentException("Illegal combo operand $this")
            }

        companion object {
            fun fromOpcode(opcode: Int): Instruction =
                entries.firstOrNull { it.opcode == opcode } ?: throw IllegalArgumentException("Unknown opcode $opcode")
        }
    }

    companion object {
        fun fromInput(input: List<String>): Day17 {
            val numbersPerLine = input.map { it.toInts() }
            return Day17(
                initialRegisterValues = numbersPerLine.take(3).flatten().map { it.toLong() },
                program = numbersPerLine.last()
            )
        }
    }
}



