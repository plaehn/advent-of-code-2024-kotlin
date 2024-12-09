package org.plaehn.adventofcode

class Day09(
    private val diskMap: String
) {

    fun solvePart1(): Long =
        diskMap
            .toFileOrFreeSpace()
            .compactByMovingBlocks()
            .computeCheckSum()

    private fun String.toFileOrFreeSpace(): List<FileOrFreeSpace> {
        var fileId = 0
        val blocks = mapIndexed { idx, ch ->
            val blockLength = ch.digitToInt()
            if (idx % 2 == 0) {
                File(id = fileId++, length = blockLength)
            } else {
                FreeSpace(length = blockLength)
            }
        }
        return blocks
    }

    private fun List<FileOrFreeSpace>.compactByMovingBlocks(): List<FileOrFreeSpace> {
        val compacted = buildList {
            val blocks = this@compactByMovingBlocks.map { it.toBlocks() }.flatten()
            val deque = blocks.toDeque()
            blocks.indices.forEach { idx ->
                when {
                    deque.isEmpty() -> add(FreeSpace(length = 1))

                    blocks[idx] is File -> add(deque.removeFirst())

                    else -> {
                        while (deque.isNotEmpty()) {
                            val last = deque.removeLast()
                            if (last is File) {
                                add(last)
                                break
                            }
                        }
                        deque.removeFirstOrNull()
                    }
                }
            }
        }

        return compacted
    }

    private fun List<FileOrFreeSpace>.toDeque(): ArrayDeque<FileOrFreeSpace> =
        ArrayDeque<FileOrFreeSpace>().apply { addAll(this@toDeque.toList()) }

    private fun List<FileOrFreeSpace>.computeCheckSum(): Long =
        this.filterIsInstance<File>()
            .mapIndexed { idx, file ->
                idx * file.id.toLong()
            }
            .sum()

    fun solvePart2(): Int {
        return 0
    }

    sealed interface FileOrFreeSpace {
        fun toBlocks(): List<FileOrFreeSpace>

        val length: Int
    }

    data class File(
        val id: Int,
        override val length: Int
    ) : FileOrFreeSpace {
        override fun toBlocks(): List<FileOrFreeSpace> =
            List(length) { File(id, 1) }
    }

    data class FreeSpace(
        override val length: Int
    ) : FileOrFreeSpace {
        override fun toBlocks(): List<FreeSpace> =
            List(length) { FreeSpace(1) }
    }
}


