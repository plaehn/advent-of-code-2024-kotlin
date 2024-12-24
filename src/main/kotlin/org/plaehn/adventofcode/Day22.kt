package org.plaehn.adventofcode

class Day22(private val secretNumbers: List<Long>) {

    fun solvePart1(): Long =
        secretNumbers.sumOf { computeNthSecretNumber(it, 2000) }

    private fun computeNthSecretNumber(current: Long, nth: Int): Long {
        if (nth == 0) return current
        var next = (current * 64).mix(current).prune()
        next = (next / 32).mix(next).prune()
        next = (next * 2048).mix(next).prune()
        return computeNthSecretNumber(next, nth - 1)
    }

    private fun Long.mix(secret: Long): Long =
        this xor secret

    private fun Long.prune(): Long =
        this % 16777216

    fun solvePart2(): Long {
        return 0
    }
}
