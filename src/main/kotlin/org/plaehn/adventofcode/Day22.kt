package org.plaehn.adventofcode

class Day22(private val secretNumbers: List<Long>) {

    fun solvePart1(): Long =
        secretNumbers.map { computeSecretNumbers(it) }.sumOf { it.last() }

    fun solvePart2(): Int {
        val differenceLists: List<List<Pair<Int, Int>>> =
            secretNumbers
                .map { computeSecretNumbers(it) }
                .map { secretNumbers -> secretNumbers.map { (it % 10).toInt() } }
                .map { secretNumbers -> secretNumbers.zipWithNext { prev, current -> current to (current - prev) } }

        val quadruple2BananasForAllLists: List<Map<List<Int>, Int>> = differenceLists.map { differenceList ->
            val quadruple2Bananas: MutableMap<List<Int>, Int> = mutableMapOf()
            differenceList
                .windowed(size = 4)
                .forEach { window: List<Pair<Int, Int>> ->
                    quadruple2Bananas.getOrPut(window.map { it.second }) { window.last().first }
                }
            quadruple2Bananas.toMap()
        }
        val quadruple2SummedBananas: Map<List<Int>, Int> =
            quadruple2BananasForAllLists.reduce { prev: Map<List<Int>, Int>, current: Map<List<Int>, Int> ->
                (prev.keys + current.keys).associateWith { key ->
                    prev.getOrDefault(key, 0) + current.getOrDefault(key, 0)
                }
            }
        return quadruple2SummedBananas.maxBy { it.value }.value
    }

    private fun computeSecretNumbers(secret: Long): List<Long> =
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
