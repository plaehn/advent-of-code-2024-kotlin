package org.plaehn.adventofcode

class Day19(private val towels: List<String>, private val designs: List<String>) {

    fun solvePart1(): Int =
        designs.count { countPossibleDesigns(it) > 0 }

    fun solvePart2(): Long =
        designs.sumOf { countPossibleDesigns(it) }

    private fun countPossibleDesigns(design: String, seen: MutableMap<String, Long> = mutableMapOf()): Long =
        if (design.isEmpty()) {
            1
        } else {
            seen.getOrPut(design) {
                towels
                    .filter { design.startsWith(it) }
                    .sumOf { countPossibleDesigns(design.removePrefix(it), seen) }
            }
        }

    companion object {
        fun fromInput(lines: List<String>): Day19 {
            val towels = lines.first().split(",").map { it.trim() }
            val designs = lines.drop(1)
            return Day19(towels, designs)
        }
    }
}

