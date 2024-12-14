package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix

class Day12(lines: List<String>) {

    private val regions = Matrix
        .fromRows(lines.map { it.toCharArray().toList() }, '.')
        .partitionIntoRegions()

    fun solvePart1(): Int =
        regions.sumOf { it.area() * it.perimeter() }

    fun solvePart2(): Int =
        regions.sumOf { it.area() * it.sides() }

    private fun Matrix<Char>.partitionIntoRegions(): Set<Region> {
        val plots: MutableMap<Coord, Char> = toMap().toMutableMap()
        val regions: MutableSet<Region> = mutableSetOf()

        while (plots.isNotEmpty()) {
            val plot = plots.entries.first().toPlot()

            regions.add(Region(findRegion(plot, mutableSetOf(), plots)))
        }

        return regions
    }

    private fun findRegion(plot: Plot, region: MutableSet<Plot>, plots: MutableMap<Coord, Char>): Set<Plot> {
        region.add(plot)
        plots.remove(plot.position)

        val neighborsInSameRegion = plot.position.neighbors().filter { neighbor ->
            plots.getOrDefault(neighbor, '.') == plot.plant
        }
        neighborsInSameRegion.forEach { neighbor ->
            if (plots.containsKey(neighbor)) {
                region.addAll(findRegion(Plot(neighbor, plots.getValue(neighbor)), region, plots))
            }
        }

        return region
    }

    private fun MutableMap.MutableEntry<Coord, Char>.toPlot() = Plot(key, value)

    data class Plot(
        val position: Coord,
        val plant: Char
    )

    data class Region(
        val plots: Set<Plot>
    ) {
        private val positions = plots.map { it.position }.toSet()

        fun area(): Int =
            plots.size

        fun perimeter(): Int =
            plots.sumOf { plot ->
                plot.position
                    .neighbors()
                    .count { neighbor -> neighbor !in positions }
            }

        fun sides(): Int {
            TODO("Not yet implemented")
        }
    }
}


