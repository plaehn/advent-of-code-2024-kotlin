package org.plaehn.adventofcode

class Day22(private val secretNumbers: List<Long>) {

    fun solvePart1(): Long =
        secretNumbers.sumOf { it.computeSecretNumbers().drop(2000).first() }

    fun solvePart2() =
        buildMap {
            secretNumbers
                .map { it.computeSecretNumbers().take(2001) }
                .map { secretNumbers -> secretNumbers.map { (it % 10).toInt() } }
                .forEach { secretNumbers ->
                    secretNumbers
                        .windowed(size = 5)
                        .map { it.zipWithNext { prev, current -> current - prev } to it.last() }
                        .distinctBy { it.first }
                        .forEach { (quadruple, bananas) ->
                            this[quadruple] = (this[quadruple] ?: 0) + bananas
                        }
                }
        }.maxOf { it.value }

    private fun Long.computeSecretNumbers(): Sequence<Long> =
        generateSequence(this) { secret ->
            secret
                .mixAndPrune { it shl 6 }
                .mixAndPrune { it shr 5 }
                .mixAndPrune { it shl 11 }
        }

    private fun Long.mixAndPrune(block: (Long) -> Long): Long =
        (this xor block(this)) % 16777216L
}
