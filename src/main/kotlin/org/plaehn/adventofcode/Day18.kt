package org.plaehn.adventofcode

import com.google.common.graph.ValueGraphBuilder
import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.ValueGraphDijkstraExt.findShortestPath
import org.plaehn.adventofcode.common.toInts

@Suppress("UnstableApiUsage")
class Day18(
    private val bytes: List<Coord>,
    private val dimension: Int,
    private val takeFirstNBytes: Int
) {

    fun solvePart1(): Int {
        val matrix = Matrix.fromDimensions(dimension, dimension, '.')
        bytes.take(takeFirstNBytes).forEach { byte ->
            matrix[byte] = '#'
        }
        val graph = buildGraph(matrix)
        val path = graph.findShortestPath(Node(Coord(0, 0), '.'), Node(Coord(dimension - 1, dimension - 1), '.'))
        return path.first().size - 1
    }

    fun solvePart2(): Int {
        return 0
    }

    private fun buildGraph(matrix: Matrix<Char>) =
        ValueGraphBuilder
            .directed()
            .expectedNodeCount(matrix.width() * matrix.height())
            .immutable<Node, Int>()
            .apply {
                matrix.toMap().forEach { (coord, chr) ->
                    val node = Node(coord, chr)
                    addNode(node)
                    matrix.neighbors(coord).forEach { neighbor ->
                        if (matrix[neighbor] == '.') {
                            putEdgeValue(node, Node(neighbor, '.'), 1)
                        }
                    }
                }
            }
            .build()

    data class Node(
        val position: Coord,
        val chr: Char
    )


    companion object {
        fun fromInput(input: List<String>, dimension: Int, take: Int) =
            Day18(
                input
                    .map { it.toInts() }
                    .map { Coord(it.first(), it.last()) },
                dimension,
                take
            )
    }
}

