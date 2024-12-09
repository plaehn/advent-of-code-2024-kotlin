package org.plaehn.adventofcode

class Day09(
    private val diskMap: String
) {

    fun solvePart1(): Long =
        diskMap
            .toBlocks()
            .compact()
            .computeCheckSum()

    private fun String.toBlocks(): List<Int> {
        var fileId = 0
        val blocks: List<Int> = mapIndexed { idx, ch ->
            if (idx % 2 == 0) {
                val file: List<Int> = List(ch.digitToInt()) { fileId }
                ++fileId
                file
            } else {
                List(ch.digitToInt()) { -1 }
            }
        }.flatten()
        println("blocks")
        println(blocks)
        println()
        return blocks
    }

    private fun List<Int>.compact(): List<Int> {
        val compacted = buildList {
            val deque = this@compact.toDeque()
            this@compact.indices.forEach { idx ->
                when {
                    deque.isEmpty() -> add(-1)
                    this@compact[idx] >= 0 -> add(deque.removeFirst())
                    else -> {
                        while (deque.isNotEmpty()) {
                            val last = deque.removeLast()
                            if (last >= 0) {
                                add(last)
                                break
                            }
                        }
                        deque.removeFirstOrNull()
                    }
                }
            }
        }
        println("compacted")
        println(compacted)
        println()
        return compacted
    }

    private fun List<Int>.toDeque(): ArrayDeque<Int> =
        ArrayDeque<Int>().apply { addAll(this@toDeque.toList()) }

    private fun List<Int>.computeCheckSum(): Long =
        this.filter { it >= 0 }
            .mapIndexed { idx, fileId ->
                (idx * fileId).toLong()
            }
            .sum()

    fun solvePart2(): Int {
        return 0
    }
}


