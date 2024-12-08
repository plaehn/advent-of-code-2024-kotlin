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
        findAllAntinodes().size

    private fun findAllAntinodes(): Set<Coord> =
        buildSet {
            findAntennaPositions()
                .forEach { positions ->
                    addAll(positions.enumerateAllPairs().computeAntinodes())
                }
        }

    private fun findAntennaPositions(): Collection<Collection<Coord>> {
        val antennaPositions: Multimap<Char, Coord> = ArrayListMultimap.create()
        antennaMap.toMap()
            .filter { it.value != '.' }
            .forEach { (position, ch) ->
                antennaPositions.put(ch, position)
            }
        return antennaPositions.asMap().values
    }

    private fun Collection<Coord>.enumerateAllPairs() =
        toSet().combinations(ofSize = 2)

    private fun Collection<Collection<Coord>>.computeAntinodes() =
        flatMap { pair ->
            pair.flatMap { position ->
                position
                    .enumerateAntinodes(distance = pair.first() - pair.last())
                    .filter { useResonantHarmonics || it !in pair }
            }
        }.toSet()

    private fun Coord.enumerateAntinodes(distance: Coord) =
        buildSet {
            if (useResonantHarmonics) this.add(this@enumerateAntinodes)
            listOf(Coord::plus, Coord::minus).forEach { operation ->
                var newPosition = this@enumerateAntinodes
                while (true) {
                    newPosition = operation(newPosition, distance)
                    if (!newPosition.isOnTheMap()) break
                    add(newPosition)
                    if (!useResonantHarmonics) break
                }
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


