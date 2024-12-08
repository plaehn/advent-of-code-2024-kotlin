package org.plaehn.adventofcode

import com.google.common.collect.ArrayListMultimap
import com.google.common.collect.Multimap
import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.combinations

class Day08(
    private val antennaMap: Matrix<Char>,
    private val useResonantHarmonics: Boolean
) {

    fun solve(): Int =
        buildSet {
            findAntennaPositions()
                .forEach { positions ->
                    addAll(positions.computeAntinodes())
                }
        }.size

    private fun findAntennaPositions(): Collection<Collection<Coord>> {
        val antennaPositions: Multimap<Char, Coord> = ArrayListMultimap.create()
        antennaMap.toMap().forEach { (position, ch) ->
            if (ch != '.') {
                antennaPositions.put(ch, position)
            }
        }
        return antennaPositions.asMap().values
    }

    private fun Collection<Coord>.computeAntinodes(): Set<Coord> =
        buildSet {
            this@computeAntinodes.toSet().combinations(ofSize = 2).forEach { pair ->
                val distance = pair.first() - pair.last()
                pair.forEach { position ->
                    if (useResonantHarmonics) add(position)
                    listOf(Coord::plus, Coord::minus).forEach { op ->
                        var newPosition = position
                        while (true) {
                            newPosition = op(newPosition, distance)
                            addIfAntinode(newPosition, pair)
                            if (!newPosition.isOnTheMap()) break
                            if (!useResonantHarmonics) break
                        }
                    }
                }
            }
        }

    private fun MutableSet<Coord>.addIfAntinode(candidate: Coord, pair: Set<Coord>) {
        if (candidate.isOnTheMap() && candidate !in pair) {
            add(candidate)
        }
    }

    private fun Coord.isOnTheMap() =
        this.x in 0..<antennaMap.width() && this.y in 0..<antennaMap.height()

    companion object {
        fun fromInput(lines: List<String>, useResonantHarmonics: Boolean = false) =
            Day08(
                Matrix.fromRows(lines.map { it.toCharArray().toList() }, '.'),
                useResonantHarmonics
            )
    }
}


