package org.plaehn.adventofcode

class Day09(
    private val diskMap: String
) {

    fun solvePart1(): Long =
        diskMap
            .toFragments()
            .compactByMovingBlocks()
            .computeCheckSum()

    fun solvePart2(): Long =
        diskMap
            .toFragments()
            .compactByMovingFiles()
            .flatMap { fragment -> fragment.toBlocks() }
            .computeCheckSum()

    private fun String.toFragments(): List<Fragment> {
        var fileId = 0
        val fragments = mapIndexed { idx, ch ->
            val blockLength = ch.digitToInt()
            if (idx % 2 == 0) {
                File(id = fileId++, length = blockLength)
            } else {
                FreeSpace(length = blockLength)
            }
        }
        return fragments
    }

    private fun List<Fragment>.compactByMovingBlocks(): List<Fragment> =
        buildList {
            val blocks = this@compactByMovingBlocks.map { it.toBlocks() }.flatten()
            val deque = blocks.toDeque()
            blocks.indices.forEach { idx ->
                when {
                    deque.isEmpty() -> add(FreeSpace(length = 1))   // fill up with '.'
                    blocks[idx] is File -> add(deque.removeFirst()) // use file block as is
                    blocks[idx] is FreeSpace -> swapFreeSpaceWithLastFileBlock(deque)
                }
            }
        }

    private fun List<Fragment>.toDeque(): ArrayDeque<Fragment> =
        ArrayDeque<Fragment>().apply { addAll(this@toDeque.toList()) }

    private fun MutableList<Fragment>.swapFreeSpaceWithLastFileBlock(deque: ArrayDeque<Fragment>) {
        while (deque.isNotEmpty()) {
            val last = deque.removeLast()
            if (last is File) {
                add(last)
                break
            }
        }
        deque.removeFirstOrNull()
    }

    private fun List<Fragment>.computeCheckSum(): Long =
        mapIndexed { idx, fragment ->
            val fileId = if (fragment is File) fragment.id else 0
            idx * fileId
        }.sumOf { it.toLong() }

    private fun List<Fragment>.compactByMovingFiles(): List<Fragment> {
        var result = this
        while (true) {
            val (indexOfFreeFragment, indexOfFileToMove) = result.findSwapIndices() ?: break
            result = result.computeNextList(indexOfFreeFragment, indexOfFileToMove)
        }
        return result
    }

    private fun List<Fragment>.findSwapIndices(): Pair<Int, Int>? =
        indices
            .reversed()
            .filter { this[it] is File }
            .firstNotNullOfOrNull { indexOfFileToMove ->
                val indexOfFreeFragment = findFreeFragmentIndex(ofSize = this[indexOfFileToMove].length)
                if (indexOfFreeFragment != -1 && indexOfFreeFragment < indexOfFileToMove) {
                    indexOfFreeFragment to indexOfFileToMove
                } else {
                    null
                }
            }

    private fun List<Fragment>.findFreeFragmentIndex(ofSize: Int): Int =
        indexOfFirst { fragment ->
            fragment is FreeSpace && fragment.length >= ofSize
        }

    private fun List<Fragment>.computeNextList(
        indexOfFreeFragment: Int,
        indexOfFileToMove: Int
    ): List<Fragment> {
        var newList = this.subList(0, indexOfFreeFragment) + this[indexOfFileToMove]

        val remainingSpace = this[indexOfFreeFragment].length - this[indexOfFileToMove].length
        if (remainingSpace > 0) {
            newList = newList + FreeSpace(remainingSpace)
        }
        newList = newList + this.subList(indexOfFreeFragment + 1, indexOfFileToMove)
        newList = newList + FreeSpace(this[indexOfFileToMove].length)
        newList = newList + this.subList(indexOfFileToMove + 1, this.size)

        return newList
    }


    sealed interface Fragment {
        fun toBlocks(): List<Fragment>

        val length: Int
    }

    data class File(
        val id: Int,
        override val length: Int
    ) : Fragment {
        override fun toBlocks(): List<Fragment> =
            List(length) { File(id, 1) }

        override fun toString() =
            id.toString().repeat(length)
    }

    data class FreeSpace(
        override val length: Int
    ) : Fragment {
        override fun toBlocks(): List<FreeSpace> =
            List(length) { FreeSpace(1) }

        override fun toString() =
            ".".repeat(length)
    }
}


