package org.plaehn.adventofcode

import com.google.common.graph.GraphBuilder
import org.plaehn.adventofcode.common.combinations

class Day23(private val connections: List<String>) {

    fun solvePart1(): Int =
        buildConnectionGraph().run {
            nodes()
                .filter { it.startsWith("t") }
                .flatMap { node ->
                    adjacentNodes(node)
                        .combinations(ofSize = 2)
                        .filter { otherNodes -> hasEdgeConnecting(otherNodes.first(), otherNodes.last()) }
                        .map { otherNodes -> otherNodes + node }
                }
                .toSet()
                .size
        }

    fun solvePart2(): String {
        return ""
    }

    private fun buildConnectionGraph() =
        GraphBuilder
            .undirected()
            .expectedNodeCount(connections.size * 2)
            .immutable<String>()
            .apply {
                connections.forEach { connection ->
                    val (lhs, rhs) = connection.split("-")
                    addNode(lhs)
                    addNode(rhs)
                    putEdge(lhs, rhs)
                }
            }
            .build()
}
