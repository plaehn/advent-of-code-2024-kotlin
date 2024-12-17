package org.plaehn.adventofcode

import com.google.common.math.IntMath.pow

class Day17(
    private val initialRegisterValues: List<Int>,
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

    fun solvePart2(): String {
        return ""
    }

    data class ComputerState(
        val registerA: Int,
        val registerB: Int,
        val registerC: Int,
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
                        registerA = state.registerA / pow(2, operand.toComboOperand(state)),
                        instructionPointer = state.instructionPointer + 2
                    )

                BDV ->
                    state.copy(
                        registerB = state.registerA / pow(2, operand.toComboOperand(state)),
                        instructionPointer = state.instructionPointer + 2
                    )

                CDV ->
                    state.copy(
                        registerC = state.registerA / pow(2, operand.toComboOperand(state)),
                        instructionPointer = state.instructionPointer + 2
                    )

                BXL ->
                    state.copy(
                        registerB = state.registerB xor operand,
                        instructionPointer = state.instructionPointer + 2
                    )

                BST ->
                    state.copy(
                        registerB = operand.toComboOperand(state) % 8,
                        instructionPointer = state.instructionPointer + 2
                    )

                JNZ ->
                    if (state.registerA == 0) {
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
                        output = state.output + listOf(operand.toComboOperand(state) % 8),
                        instructionPointer = state.instructionPointer + 2
                    )
            }

        private fun Int.toComboOperand(state: ComputerState): Int =
            when {
                this <= 3 -> this
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
            val numbersPerLine = input.map { it.toNumbers() }
            return Day17(
                initialRegisterValues = numbersPerLine.take(3).flatten(),
                program = numbersPerLine.last()
            )
        }

        private fun String.toNumbers(): List<Int> =
            "-?\\d+".toRegex().findAll(this).map { it.value.toInt() }.toList()
    }
}



