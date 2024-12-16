package org.plaehn.adventofcode

import com.google.common.graph.ValueGraphBuilder
import org.plaehn.adventofcode.common.Coord
import org.plaehn.adventofcode.common.Matrix
import org.plaehn.adventofcode.common.ValueGraphDfsExt.findAllPaths

class Day10(lines: List<String>) {

    private val matrix = Matrix.fromRows(lines.map { it.toCharArray().map { digit -> digit.digitToInt() } }, -1)
    private val graph = buildTrailGraph()
    private val trailHeads = graph.nodes().filter { it.height == 0 }
    private val destinations = graph.nodes().filter { it.height == 9 }

    fun solvePart1(): Int =
        trailHeads.sumOf { trailHead ->
            val trails = findAllTrailsStartingAt(trailHead)
            val distinctDestinations = trails.map { it.last().coord }.toSet()
            distinctDestinations.size
        }

    fun solvePart2(): Int =
        trailHeads.sumOf { trailHead ->
            findAllTrailsStartingAt(trailHead).size
        }

    private fun findAllTrailsStartingAt(trailHead: Node): List<List<Node>> {
        val trails = destinations.flatMap { destination ->
            graph.findAllPaths(trailHead, destination)
        }
        return trails
    }

    private fun buildTrailGraph() =
        ValueGraphBuilder
            .directed()
            .expectedNodeCount(matrix.width() * matrix.height())
            .immutable<Node, Int>()
            .apply {
                matrix.toMap().keys.forEach { coord ->
                    findNextTrailSteps(coord).forEach { nextStep ->
                        val node = Node(coord, matrix[coord])
                        val nextNode = Node(nextStep, matrix[nextStep])
                        addNode(node)
                        addNode(nextNode)
                        putEdgeValue(node, nextNode, 1)
                    }
                }
            }
            .build()

    private fun findNextTrailSteps(coord: Coord) =
        matrix
            .neighbors(coord)
            .filter { neighbor -> matrix[neighbor] - matrix[coord] == 1 }

    data class Node(
        val coord: Coord,
        val height: Int
    )
}