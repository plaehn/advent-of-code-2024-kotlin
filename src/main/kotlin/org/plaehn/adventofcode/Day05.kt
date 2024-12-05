package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.chunkByBlankLines


class Day05(input: String) {

    private val rules: List<String>
    private var updates: List<String>

    init {
        val (rules, updates) = input.chunkByBlankLines()
        this.rules = rules
        this.updates = updates.filter { it.isNotBlank() }
    }

    fun solvePart1(): Int =
        updates
            .map { update -> update.split(",").map { it.toInt() } }
            .filter { pageNumbers -> pageNumbers.isOrdered(rules.toPageComparator()) }
            .sumOf { it.middleElement() }

    private fun List<String>.toPageComparator(): Comparator<Int> =
        Comparator { lhs, rhs ->
            when {
                "$lhs|$rhs" in this -> -1
                "$rhs|$lhs" in this -> 1
                else -> 0
            }
        }

    private fun List<Int>.middleElement() = this[size / 2]

    private fun List<Int>.isOrdered(comparator: Comparator<Int>) = this == this.sortedWith(comparator)

    fun solvePart2(): Int {
        return 0
    }
}



