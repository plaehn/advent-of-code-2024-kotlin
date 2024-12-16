package org.plaehn.adventofcode

import com.google.common.graph.ValueGraph
import com.google.common.graph.ValueGraphBuilder
import org.plaehn.adventofcode.Day16.Direction.*
import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.ValueGraphDijkstraExt.findShortestPath

@Suppress("UnstableApiUsage")
class Day16(
    private val labyrinth: Matrix<Char>
) {

    fun solvePart1(): Long =
        findBestPathsWithCost().first().second

    fun solvePart2(): Int =
        findBestPathsWithCost()
            .flatMap { it.first }
            .map { it.position }
            .toSet().size

    private fun findBestPathsWithCost(): List<Pair<List<Node>, Long>> {
        val graph = buildGraph()
        val startNode = Node(labyrinth.find('S'), EAST)
        val endPosition = labyrinth.find('E')
        val paths = Direction.entries.flatMap { direction ->
            val paths = graph.findShortestPath(startNode, Node(endPosition, direction)).toMutableList()
            paths.map { mutableListOf(startNode) + it } // TODO this should not be here
        }.filter { it.last().position == endPosition } // TODO this should not be here

        val shortestPathsWithCost = paths.map { path -> path to graph.computeCost(path) }
        val minimalCost = shortestPathsWithCost.minOfOrNull { it.second }
        return shortestPathsWithCost.filter { it.second == minimalCost }
    }

    private fun ValueGraph<Node, Int>.computeCost(path: List<Node>): Long =
        path.zipWithNext { current, next -> edgeValue(current, next).get().toLong() }
            .sum()

    private fun Matrix<Char>.find(target: Char): Coord =
        toMap().filter { (_, chr) -> chr == target }.keys.first()

    private fun buildGraph() =
        ValueGraphBuilder
            .directed()
            .expectedNodeCount(labyrinth.width() * labyrinth.height())
            .immutable<Node, Int>()
            .apply {
                labyrinth.toMap().keys
                    .filterNot { labyrinth[it] == '#' }
                    .forEach { position ->
                        val nodeNorth = Node(position, NORTH)
                        val nodeEast = Node(position, EAST)
                        val nodeSouth = Node(position, SOUTH)
                        val nodeWest = Node(position, WEST)

                        addNode(nodeNorth)
                        addNode(nodeEast)
                        addNode(nodeSouth)
                        addNode(nodeWest)

                        putEdgeValue(nodeNorth, nodeEast, 1000)
                        putEdgeValue(nodeNorth, nodeWest, 1000)
                        putEdgeValue(nodeEast, nodeSouth, 1000)
                        putEdgeValue(nodeEast, nodeNorth, 1000)
                        putEdgeValue(nodeSouth, nodeWest, 1000)
                        putEdgeValue(nodeSouth, nodeEast, 1000)
                        putEdgeValue(nodeWest, nodeNorth, 1000)
                        putEdgeValue(nodeWest, nodeSouth, 1000)

                        entries.forEach { direction ->
                            if (labyrinth[position + direction.offset] != '#') {
                                val neighborNode = Node(position + direction.offset, direction)
                                putEdgeValue(Node(position, direction), neighborNode, 1)
                            }
                        }
                    }
            }
            .build()


    companion object {
        fun fromInput(input: List<String>) =
            Day16(Matrix.fromRows(input.map { it.toCharArray().toList() }, '.'))
    }

    data class Node(
        val position: Coord,
        val direction: Direction
    )

    enum class Direction(val offset: Coord) {
        NORTH(Coord(0, -1)),
        EAST(Coord(1, 0)),
        SOUTH(Coord(0, 1)),
        WEST(Coord(-1, 0))
    }
}

