package org.plaehn.adventofcode

class Day22(private val secretNumbers: List<Long>) {

    fun solvePart1(): Long =
        secretNumbers.map { computeListsOfSecretNumbers(it) }.sumOf { it.last() }

    fun solvePart2(): Long {
        return 0
    }

    private fun computeListsOfSecretNumbers(secret: Long): List<Long> =
        sequence {
            var current = secret
            (0..2000).map {
                this.yield(current)
                current = current.computeNextSecretNumber()
            }
        }.toList()

    private fun Long.computeNextSecretNumber(): Long {
        var next = (this * 64).mix(this).prune()
        next = (next / 32).mix(next).prune()
        next = (next * 2048).mix(next).prune()
        return next
    }

    private fun Long.mix(secret: Long): Long =
        this xor secret

    private fun Long.prune(): Long =
        this % 16777216
}
