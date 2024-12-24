package org.plaehn.adventofcode

import java.time.LocalTime

class Day22(private val secretNumbers: List<Long>) {

    fun solvePart1(): Long =
        secretNumbers.map { computeSecretNumbers(it) }.sumOf { it.last() }

    fun solvePart2(): Int {
        println("Start: " + LocalTime.now())
        val differenceLists: List<List<Pair<Int, Int>>> =
            secretNumbers
                .map { computeSecretNumbers(it) }
                .map { secretNumbers -> secretNumbers.map { (it % 10).toInt() } }
                .map { secretNumbers -> secretNumbers.zipWithNext { prev, current -> current to (current - prev) } }
        println("Diffs: " + LocalTime.now())

        val quadruple2BananasForAllLists: List<Map<List<Int>, Int>> = differenceLists.map { differenceList ->
            val quadruple2Bananas: MutableMap<List<Int>, Int> = mutableMapOf()
            differenceList
                .windowed(size = 4)
                .forEach { window: List<Pair<Int, Int>> ->
                    quadruple2Bananas.getOrPut(window.map { it.second }) { window.last().first }
                }
            quadruple2Bananas.toMap()
        }
        println("Quads: " + LocalTime.now())

        val allQuadruples = quadruple2BananasForAllLists.flatMap { it.keys }.toSet()
        println("Keys:  " + LocalTime.now())

        var maxBananas = 0
        allQuadruples.forEach { quadruple ->
            var currentBananas = 0
            quadruple2BananasForAllLists.forEachIndexed { index, quadruple2Bananas ->
                currentBananas += quadruple2Bananas.getOrDefault(quadruple, 0)
                if (currentBananas + 9 * (quadruple2BananasForAllLists.size - index - 1) < maxBananas) {
                    return@forEach
                }
            }
            if (currentBananas > maxBananas) {
                maxBananas = currentBananas
            }
        }
        println("Max:   " + LocalTime.now())
        return maxBananas
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
