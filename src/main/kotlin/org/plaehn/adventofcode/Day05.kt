package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.chunkByBlankLines


class Day05(input: String) {

    private val rules: List<String>
    private var updates: List<List<Int>>
    private val pageComparator: Comparator<Int>

    init {
        val (rules, updates) = input.chunkByBlankLines()
        this.rules = rules
        this.updates = updates
            .filter { it.isNotBlank() }
            .map { update -> update.split(",").map { it.toInt() } }
        this.pageComparator = createPageComparator(rules)
    }

    fun solvePart1(): Int =
        updates
            .filter { it.isOrdered() }
            .sumOf { it.middleElement() }

    fun solvePart2(): Int =
        updates
            .filterNot { it.isOrdered() }
            .map { it.sortedWith(pageComparator) }
            .sumOf { it.middleElement() }

    private fun createPageComparator(rules: List<String>) =
        Comparator<Int> { lhs, rhs ->
            when {
                "$lhs|$rhs" in rules -> -1
                "$rhs|$lhs" in rules -> 1
                else -> 0
            }
        }

    private fun List<Int>.middleElement() = this[size / 2]

    private fun List<Int>.isOrdered() = this == this.sortedWith(pageComparator)
}



