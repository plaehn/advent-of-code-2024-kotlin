package org.plaehn.adventofcode

import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.chunked

class Day12(lines: List<String>) {

    private val regions = Matrix
        .fromRows(lines.map { it.toCharArray().toList() }, '.')
        .partitionIntoRegions()

    fun solvePart1(): Int =
        regions.sumOf { it.area() * it.perimeter() }

    fun solvePart2(): Int =
        regions.sumOf { it.area() * it.sides() }

    private fun Matrix<Char>.partitionIntoRegions(): Set<Region> {
        val plots = toMap().toMutableMap()
        val regions = mutableSetOf<Region>()

        while (plots.isNotEmpty()) {
            val plot = plots.entries.first().toPlot()
            regions.add(Region(findRegion(plot, plots)))
        }

        return regions
    }

    private fun findRegion(plot: Plot, plots: MutableMap<Coord, Char>): Set<Plot> {
        val region = mutableSetOf<Plot>()
        region.add(plot)
        plots.remove(plot.position)

        val neighborsWithSamePlant = plot.position.neighbors().filter { neighbor ->
            plots.getOrDefault(neighbor, '.') == plot.plant
        }
        neighborsWithSamePlant.forEach { neighbor ->
            if (plots.containsKey(neighbor)) {
                region.addAll(findRegion(Plot(neighbor, plots.getValue(neighbor)), plots))
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

        fun sides(): Int =
            Direction.entries.sumOf { direction ->
                val neighborsInDirection = plots
                    .map { plot -> plot.position + direction.offset }
                    .filter { neighbor -> neighbor !in positions }
                val groupedByFacingDimension = neighborsInDirection.groupBy(direction.facingDimension)
                groupedByFacingDimension.values.sumOf { plots ->
                    val contiguousChunks = plots
                        .map(direction.nonFacingDimension)
                        .sorted()
                        .chunked { left, right -> right - left > 1 }
                    contiguousChunks.count()
                }
            }
    }

    enum class Direction(
        val offset: Coord,
        val facingDimension: (Coord) -> Int,
        val nonFacingDimension: (Coord) -> Int
    ) {
        UP(Coord(0, -1), Coord::y, Coord::x),
        DOWN(Coord(0, 1), Coord::y, Coord::x),
        LEFT(Coord(-1, 0), Coord::x, Coord::y),
        RIGHT(Coord(1, 0), Coord::x, Coord::y)
    }
}


